/**
  * Created by gregeusden on 12/2/17.
  */

import scala.io.Source

object SolverDay2 extends App {
  val solver = new Day2P2()
  solver.run()
}

class Day2() {

  def run() : Unit = {

    var total : Int = 0
    val filename = "src/main/resources/PromptD2P1.txt"

    for (line <- Source.fromFile(filename).getLines) {
      val text = line

      total += process(line)
    }
    println(total)
  }

  def process(text : String): Int = {

    val nums : Array[String] = text.split("\t")
    var min = nums(0).toInt
    var max = nums(0).toInt

    for(num <- nums) {
      val x = num.toInt
      if (x < min) min = x
      if (x > max) max = x
    }

    return max - min
  }
}

class Day2P2() {

  def run() : Unit = {

    var total : Int = 0
    val filename = "src/main/resources/PromptD2P1.txt"

    for (line <- Source.fromFile(filename).getLines) {
      val text = line

      total += process(line)
    }
    println(total)
  }

  def process(text : String): Int = {

    val nums : Array[String] = text.split("\t")
    var min = nums(0).toInt
    var max = nums(0).toInt

    for(x <- 0 until nums.size) {
      val comparator = nums(x).toInt
      for (y <- x+1 until nums.size) {
        val comparee = nums(y).toInt
        if (comparee > comparator) {
          if (comparee % comparator == 0) return comparee/comparator
        } else {
          if (comparator % comparee == 0) return comparator/comparee
        }
      }
    }
    assert(false, "ERROR, no divisors")
    return -1
  }
}


