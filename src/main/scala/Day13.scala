//import scala.collection.mutable.ListBuffer
import scala.io.Source

/**
  * Created by gregeusden on 12/4/17.
  */
object SolverDay13 extends App {
  val solver = new Day13()
  solver.run()
}

class Day13() {

  //var programs: Array[Disc] = new Array[Disc](1098)


  def run(): Unit = {

    val firewallLength = new Array[Int](100)
    var total = 0

    val filename = "src/main/resources/PromptD13P1.txt"

    for (line <- Source.fromFile(filename).getLines) {
      val wallIndex = line.substring(0,line.indexOf(":")).toInt
      val wallLength = line.substring(line.indexOf(" ")+1).toInt

      firewallLength(wallIndex) = wallLength
    }

    for (x <- 0 until firewallLength.length) {
      if (getScannerPosition(x, firewallLength(x)) == 0) {
        total += x*firewallLength(x)
      }

    }
    println("Total:" + total)

    var acceptableLaunch = new Array[Int](100000000)
    for (time <- 0 until firewallLength.length) {
      if (firewallLength(time) != 0) {
        setBadTimes(time, firewallLength(time), acceptableLaunch)
      }

    }

    var best = -1
    var index = 0

    while (best == -1) {
      if (acceptableLaunch(index) == 0) {
        best = index
      }
      if (index % 10000 == 0) {
        println("TimeCheck: " + index)
      }
      index += 1
    }
    println("Best is: " + best)
  }

  def setBadTimes(time :Int, depth : Int, list : Array[Int]) : Unit = {
    val offset = time
    val multiple = (depth*2)-2
    var temp = (-1)*offset
    while (temp < list.length) {
      if (temp >= 0) {
        list(temp) = 1
      }
      temp += multiple
    }
  }

  def getScannerPosition(time : Int, depth : Int) : Int = {
    if (depth == 0) {
      return -1
    } else if (time == 0) {
      return 0
    } else {
      val pos = (time % (depth*2-2))
      if (pos < depth) {
        return pos
      } else {
        return (depth-1) - (pos-(depth-1))
      }
    }

  }
}
