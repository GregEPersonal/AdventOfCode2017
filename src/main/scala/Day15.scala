//import scala.collection.mutable.ListBuffer
import scala.io.Source

/**
  * Created by gregeusden on 12/4/17.
  */
object SolverDay15 extends App {
  val solver = new Day15()
  solver.run()
}

class Day15() {

  //var programs: Array[Disc] = new Array[Disc](1098)

  val aMult : Long = 16807
  val bMult : Long = 48271

  val divisor : Long = 2147483647
  val bitWiseAnd : Long = math.pow(2.0,16.0).toInt-1

  def run(): Unit = {
    var aVal: Long = 699
    var bVal: Long = 124

    //val part = 1
    val part = 2

    var maxCount = 40000000
    if (part == 2) {
      maxCount = 5000000
    }

    var count: Long = 0
    var total = 0

    while (count < 5000000) {
      if (part == 1) {
        aVal = (aVal * aMult) % divisor
        bVal = (bVal * bMult) % divisor
      } else if (part == 2) {
        aVal = aGetNextVal(aVal)
        bVal = bGetNextVal(bVal)
      }
      val aTemp = (aVal & bitWiseAnd)
      val bTemp = (bVal & bitWiseAnd)
      if (aTemp == bTemp) (total += 1)
      count += 1

      if (count % 1000 == 0) {
        println("Count is: " + count)
      }
    }
    println("Total: " + total)
  }

  def aGetNextVal(prev: Long) : Long = {
    var next : Long = (prev*aMult) % divisor
    while ((next & 3) != 0) {
      next = (next*aMult) % divisor
    }
    next
  }

  def bGetNextVal(prev: Long) : Long = {
    var next : Long = (prev*bMult) % divisor
    while ((next & 7) != 0) {
      next = (next*bMult) % divisor
    }
    next
  }
}