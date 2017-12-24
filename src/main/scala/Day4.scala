import scala.io.Source

/**
  * Created by gregeusden on 12/4/17.
  */
object SolverDay4 extends App {
  val solver = new Day4P2()
  solver.run()
}

class Day4() {

  def run() : Unit = {

    var total : Int = 0
    val filename = "src/main/resources/PromptD4P1.txt"

    for (line <- Source.fromFile(filename).getLines) {
      val text = line


      val strings = line.split(" ")
      var goodPhrase = true
      for (x <- 0 until strings.size) {
        val str = strings(x)
        for (y <- x+1 until strings.size) {
          if (str == strings(y)) {
            goodPhrase = false
          }
        }
      }
      if (goodPhrase) total += 1
    }
    println(total)
  }

}

class Day4P2() {

  def run(): Unit = {

    var total: Int = 0
    val filename = "src/main/resources/PromptD4P1.txt"

    for (line <- Source.fromFile(filename).getLines) {
      val text = line


      val strings = line.split(" ")
      var goodPhrase = true
      for (x <- 0 until strings.size) {
        val str = strings(x)
        for (y <- x + 1 until strings.size) {
          if (str == strings(y) || anagram(str, strings(y))) {
            goodPhrase = false
          }
        }
      }
      if (goodPhrase) total += 1
    }
    println(total)
  }

  def anagram(x: String, y: String): Boolean = {

    return ((x.sortWith(_>_)).equals(y.sortWith(_>_)))
  }
}
