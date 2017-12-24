//import scala.collection.mutable.ListBuffer
import scala.io.Source

/**
  * Created by gregeusden on 12/4/17.
  */
object SolverDay11 extends App {
  val solver = new Day11()
  solver.run()
}

class Day11() {

  //var programs: Array[Disc] = new Array[Disc](1098)

  def run(): Unit = {

    var count: Int = 0
    val filename = "src/main/resources/PromptD11P1.txt"

    var x : Double = 0.0
    var y : Double = 0.0
    var max: Double = 0.0
    for (line <- Source.fromFile(filename).getLines) {
      val directions = line.split(",")

      for (index <- 0 until directions.length) {
        val tempx = directions(index) match {
          case "nw" => -0.5
          case "n" => 0
          case "ne" => 0.5
          case "se" => 0.5
          case "s" => 0
          case "sw" => -0.5
          case _ => 0
        }
        val tempy = directions(index) match {
          case "nw" => 0.5
          case "n" => 1.0
          case "ne" => 0.5
          case "se" => -0.5
          case "s" => -1.0
          case "sw" => -0.5
          case _ => 0
        }
        y += tempy
        x += tempx
        println(directions(index) + " X: " + x + " Y: " + y)
        if (dist(x,y) > max) {
          max = dist(x,y)
        }
      }
      println("X: " + x + " Y: " + y)
      println("Final dist: " + dist(x,y))
      println("Max dist: " + max)

    }


  }

  def dist(x: Double, y : Double) : Double = {
    if (y >= x) {
      return x*2+(y-x)
    } else {
      return x*2
    }
  }

}

