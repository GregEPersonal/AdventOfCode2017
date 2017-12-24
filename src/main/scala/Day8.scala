import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.io.Source

/**
  * Created by gregeusden on 12/4/17.
  */
object SolverDay8 extends App {
  val solver = new Day8()
  solver.run()
}

class Day8() {

  //var programs: Array[Disc] = new Array[Disc](1098)

  def run(): Unit = {

    var count: Int = 0
    val filename = "src/main/resources/PromptD8P1.txt"


    //var counts : Array[Int] = new Array[Int](1098)
    var registers : mutable.Map[String, Int] = mutable.Map()
    var maxEver = 0
    for (line <- Source.fromFile(filename).getLines) {
      val strings = line.split(" ")
      val register = strings(0)
      val action = strings(1)
      val amt = strings(2).toInt
      val testRegister = strings(4)
      val comparator = strings(5)
      val testAmt = strings(6).toInt

      var testPassed = false

      var regValue = registers.get(testRegister).getOrElse(0)

      testPassed = comparator match {
        case "==" => regValue == testAmt
        case "<=" => regValue <= testAmt
        case ">=" => regValue >= testAmt
        case "!=" => regValue != testAmt
        case ">" => regValue > testAmt
        case "<" => regValue < testAmt
        case _ => false
      }
      if (testPassed) {
        val add = action match {
          case "inc" => amt
          case "dec" => amt * (-1)
        }
        registers.put(register,add + registers.getOrElse(register,0))
      }
      if (registers.values.max >= maxEver) {
        maxEver = registers.values.max
      }
    }
    println(registers.values.max)
    println(maxEver)

  }

}