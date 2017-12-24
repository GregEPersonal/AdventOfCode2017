import scala.io.Source

/**
  * Created by gregeusden on 12/4/17.
  */
object SolverDay6 extends App {
  val solver = new Day6()
  solver.run()
}

class Day6() {

  def run() : Unit = {

    var total : Int = 0
    val filename = "src/main/resources/PromptD6P1.txt"
    //val list = new Array[Int](1070)
    var text : Array[String] = new Array[String](16)
    var nums : Array[Int] = new Array[Int](16)
    for (line <- Source.fromFile(filename).getLines) {
       text = line.split("\t")
    }
    val length = text.size
    for (x <- 0 until text.size) {
      nums(x) = text(x).toInt
    }

    var seen = false
    var checkList : Array[String] = new Array[String](10000)
    while (!seen) {
      var index = -1
      total += 1
      var largest = 0
      for (x <- 0 until nums.size) {
        if (nums(x) > largest) {
          largest = nums(x)
          index = x
        }
      }
      nums(index) = 0

      while (largest > 0) {
        index = (index + 1) % nums.size
        nums(index) += 1
        largest -=1
      }

      if (checkList.contains(toBadString(nums))) {
        seen = true
        println(total)
        println(total - checkList.indexOf(toBadString(nums)))
      } else {
        checkList(total-1) = toBadString(nums)
        println("False: " + total)
        println (toBadString(nums))
      }
     }

    //println(total)
  }

  def toBadString(nums : Array[Int]) : String = {
    var str = ""
    for (x <- nums) {
      str = str + x.toString() + " "
    }
    str
  }

}

class Day6P2() {

  def run(): Unit = {

  }
}
