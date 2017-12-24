import scala.io.Source

/**
  * Created by gregeusden on 12/4/17.
  */
object SolverDay5 extends App {
  val solver = new Day5()
  solver.run()
}

class Day5() {

  def run() : Unit = {

    var count : Int = 0
    val filename = "src/main/resources/PromptD5P1.txt"
    val list = new Array[Int](1070)

    for (line <- Source.fromFile(filename).getLines) {
      val text = line.toInt
      list(count) = text
      count += 1
      println(count)

    }
    var total = 0
    var index = 0
    while (index < list.size) {
      var tmp = index
      index += list(index)
      if ( list(tmp) >= 3)  {
        list(tmp) -= 1
      } else {
        list(tmp) += 1
      }
      total += 1
      }

  }

}

class Day5P2() {

  def run(): Unit = {

    var total: Int = 0
    val filename = "src/main/resources/PromptD5P1.txt"

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
