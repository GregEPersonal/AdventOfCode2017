//import scala.collection.mutable.ListBuffer
import scala.collection.mutable
import scala.io.Source

/**
  * Created by gregeusden on 12/4/17.
  */
object SolverDay18 extends App {
  val solver = new Day18()
  solver.run()
}

class Day18() {

  //var programs: Array[Disc] = new Array[Disc](1098)

  var lastSound : Option[Int] = None
  var index = 0

  def run(): Unit = {

    var instructions =  new Array[String](45)
    var count = 0

    val filename = "src/main/resources/PromptD18P1.txt"

    for (line <- Source.fromFile(filename).getLines) {
      instructions(count) = line
      count += 1
    }

    var complete = false

    var registers = mutable.Map[String, Int]()

    while (!complete) {
       val status = instructions(index).toCharArray.toList match {
        case 's'::'n'::'d' :: a => snd(a.mkString(""), registers)
        case 's'::'e'::'t' :: a => set(a.mkString(""), registers)
        case 'a'::'d'::'d' :: a => add(a.mkString(""), registers)
        case 'm'::'u'::'l' :: a => mul(a.mkString(""), registers)
        case 'm'::'o'::'d' :: a => mod(a.mkString(""), registers)
        case 'r'::'c'::'v' :: a => rcv(a.mkString(""), registers)
        case 'j'::'g'::'z' :: a => jgz(a.mkString(""), registers)
        case _ => println("Error")
      }
      if (status == 1) {
        complete = true
      }
      index += 1
    }
    println("Completed: " + lastSound)

  }

  def snd(strng :  String, registers : mutable.Map[String, Int]) : Int = {
    var args = strng.split(" ")
    val reg = args(1)
    lastSound = Some(registers.getOrElse(reg,0))
    return 0
  }
  def set(strng :  String, registers : mutable.Map[String, Int]) : Int = {
    var args = strng.split(" ")
    val temp = args(2)
    var num = 0

    try {
      num = temp.toInt
    } catch {
      case _: NumberFormatException =>
        num = registers.getOrElse(temp, 0)
    }
    val reg = args(1)

    return 0
  }

  def add(strng :  String, registers : mutable.Map[String, Int]) : Int = {
    var args = strng.split(" ")
    val num = args(2).toInt
    val reg = args(1)
    registers(reg) = num+registers.getOrElse(reg,0)
    return 0
  }

  def mul(strng :  String, registers : mutable.Map[String, Int]) : Int = {
    var args = strng.split(" ")
    val num = args(2).toInt
    val reg = args(1)
    registers(reg) = num*registers.getOrElse(reg,0)
    return 0
  }

  def mod(strng :  String, registers : mutable.Map[String, Int]) : Int = {
    var args = strng.split(" ")
    val num = args(2).toInt
    val reg = args(1)
    val currentVal = registers.getOrElse(reg, 0)
    var result = 0
    if (currentVal >= 0) {
      result = currentVal % num
    } else {
      result = num - (currentVal*(-1) % num)
    }
    registers(reg) = result
    return 0
  }

  def rcv(strng :  String, registers : mutable.Map[String, Int]) : Int = {
    var args = strng.split(" ")
    val num = args(2).toInt
    val reg = args(1)
    if (registers.getOrElse(reg,0) != 0) {
      println("Complete: " + lastSound)
    }
    return 1
  }

  def jgz(strng :  String, registers : mutable.Map[String, Int]) : Int = {
    var args = strng.split(" ")
    val num = args(2).toInt
    val reg = args(1)
    if (reg == "1") {
      index += num
    } else if (registers.getOrElse(reg,0) > 0) {
      index += num
    }
    return 0
  }
}