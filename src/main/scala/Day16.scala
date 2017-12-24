//import scala.collection.mutable.ListBuffer
import scala.io.Source

/**
  * Created by gregeusden on 12/4/17.
  */
object SolverDay16 extends App {
  val solver = new Day16()
  solver.run()
}

class Day16() {

  //var programs: Array[Disc] = new Array[Disc](1098)

  def run(): Unit = {

    var count: Int = 0
    val filename = "src/main/resources/PromptD16P1.txt"
    var instructionList : Array[String] = new Array[String](1)

    for (line <- Source.fromFile(filename).getLines) {
      instructionList = line.split(",")
    }

    //val letterOrder = ('a' until 'q').map(x => x.toString).toArray
    val letterOrder = ('a' until 'q').map(_.toString).toArray
    val list = letterOrder.clone()
    while (!letterOrder.mkString("").equals(list.mkString("")) || count == 0) {
      for (x <- instructionList) {
        x.toCharArray.toList match {
          case 'x' :: a => exchange(a.mkString(""), letterOrder)
          case 's' :: b => swap(b.mkString(""), letterOrder)
          case 'p' :: c => partner(c.mkString(""), letterOrder)
        }
      }
      count+=1

      if (count % 1000 == 0) {
        println("Count is: " + count)
        println(letterOrder.mkString(""))
      }
    }
    println("Final Count: " + count)
    println(letterOrder.mkString(""))
    val remainder : Int = 1000000000 % 36
    println(remainder)
    for (x <- 0 until remainder) {
      for (x <- instructionList) {
        x.toCharArray.toList match {
          case 'x' :: a => exchange(a.mkString(""), letterOrder)
          case 's' :: b => swap(b.mkString(""), letterOrder)
          case 'p' :: c => partner(c.mkString(""), letterOrder)
        }
      }
    }
    println(letterOrder.mkString(""))
    //}

//    val indexOrder = (letterOrder.map(x => list2.indexOf(x)))
//    println("Round one: " + indexOrder.mkString(","))
//    for (x <- 1 to 1000) {
//      val list2 = letterOrder.clone()
//      for (y <- 0 until list2.length) {
//        letterOrder(y) = list2(indexOrder(y))
//      }
//      println(letterOrder.mkString(""))
//    }

  }

  def exchange(inst : String, list : Array[String]) : Unit = {
    val numArray = inst.split("/")
    val num1 = numArray(0).toInt
    val num2 = numArray(1).toInt

    val temp = list(num1)
    list(num1) = list(num2)
    list(num2) = temp
  }

  def swap(inst : String, list : Array[String]) : Unit = {
    val num = inst.toInt
    val oldList = list.clone()
    for (x <- 0 until list.length) {
      list(x) = oldList((x-num+list.length)%(list.length))
    }

  }

  def partner(inst : String, list : Array[String]) : Unit = {
    val letArray = inst.split("/")
    val letter1 = letArray(0)
    val letter2 = letArray(1)

    val ind1 = list.indexOf(letter1)
    val ind2 = list.indexOf(letter2)

    list(ind1) = letter2
    list(ind2) = letter1
  }
}
