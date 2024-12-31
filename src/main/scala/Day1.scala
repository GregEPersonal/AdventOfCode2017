import scala.io.Source

object ProblemSolver extends App {
  val solver = new Day1Prob2()
  solver.run()
}

class Day1Prob1() {

  def run() : Unit = {

    println("Day 1 Problem 1")
    val filename = "src/main/resources/PromptD1P1.txt"
    for (line <- Source.fromFile(filename).getLines) {
      val text = line
      println(line)
      process(line)
    }
  }

  def process(text : String) : Unit = {
    var firstVal : Int = 0
    var priorVal : Int = 0
    var index : Int = 0
    var total : Int = 0

    for( char <- text) {
      val value = char.toInt - '0'.toInt

      if (index == 0) {
        firstVal = value
      }  else if (value == priorVal) {
        total += value
      }

      if (index == text.length() -1) {
        total += (if (value == firstVal) value else 0)
      }

      priorVal = value
      index += 1

    }
    println(total)
  }
}

class Day1Prob2() {
  def run() : Unit = {

    val filename = "src/main/resources/PromptD1P1.txt"
    for (line <- Source.fromFile(filename).getLines) {
      val text = line
      println(line)
      process(line)
    }
  }

  def process(text : String) : Unit = {

    var total : Int = 0
    val size : Int = text.length()

    for( x <- 0 until size/2) {
      val value = text(x).toInt - '0'.toInt
      val comp = text((x + size/2)).toInt - '0'.toInt

      if (value == comp) {
        total += value*2
      }
    }
    println(total)
  }
}