//import scala.collection.mutable.ListBuffer
import scala.io.Source

/**
  * Created by gregeusden on 12/4/17.
  */
object SolverDay12 extends App {
  val solver = new Day12()
  solver.run()
}

class Day12() {

  //var programs: Array[Disc] = new Array[Disc](1098)

  val results = new Array[Int](2000)
  val checked = new Array[Int](2000)
  var list : Array[Programs]  = new Array[Programs](2000)
  var groupList = new Array[Int](list.length)

  def run(): Unit = {


    val filename = "src/main/resources/PromptD12P1.txt"

    for (x <- 0 until groupList.length) {
      groupList(x) = -1
    }

    for (line <- Source.fromFile(filename).getLines) {
      val index = line.substring(0,line.indexOf(" ")).toInt

      val connStrings = line.substring(line.indexOf("<->")+4).split(", ")
      val connInt = new Array[Int](connStrings.length)
      for (x <- 0 until connInt.length) {
        connInt(x) = connStrings(x).toInt
      }
      list(index) = new Programs(index, connInt)
    }

    var groupCount = 0
    for (x <- 0 until groupList.length) {
      if (groupList(x) == -1) {
        resultsCheck(x, groupCount)
        groupCount += 1
      }
    }
    var count = 0
    for (x <- 0 until groupList.length) {
      if (groupList(x) == 0) {count += 1}
    }
    println("Group 0 Count: " + count)
    println("Total Group Count: " + groupCount)
  }

  //Stack overflow
  def resultsCheck(index : Int, origin : Int) : Unit = {
    if (checked(index) == 0) {
      results(index) = 1
      checked(index) = 1
      groupList(index) = origin
      for (p <- list(index).connections) {
        if (checked(p) == 0) {
          resultsCheck(p, origin)
        }
      }
    }
  }

}

class Programs(ind : Int, connect : Array[Int]) {
  val index = ind
  val connections = connect

}