/**
  * Created by gregeusden on 12/2/17.
  */

import sun.jvm.hotspot.debugger.win32.coff.DebugVC50SymbolEnums

import scala.io.Source

object SolverDay3 extends App {
  val solver = new Day3P2()
  solver.run()
}

class Day3() {

  def run() : Unit = {

    val input = 277678
    val closestSquare = math.sqrt(input).toInt
    val closestOddSquare = if (closestSquare % 2 == 0) closestSquare -1 else closestSquare
    val remainder = input - math.pow(closestOddSquare,2)
    val findoutlier = ((remainder-1) % (closestOddSquare+1))+1
    val outsideDist = math.abs(findoutlier - (closestOddSquare+1)/2)
    val insideDist = closestOddSquare/2+1
    println("Out: " + outsideDist)
    println ("In: " + insideDist)

    println(outsideDist + insideDist)
  }
}

class Day3P2() {
  def run(): Unit = {
    val input = 277678

    val grid : Array[Array[Cell]] = new Array[Array[Cell]](99)
    for (x <- grid.indices) {
      grid(x) = new Array[Cell](99)
    }

    grid(50)(50) = new Cell(0,0,1,1)
    var priorCell = grid(50)(50)

    try {
      //Clearly should just be a while loop, but I hadn't messed with while loops in scala
      // and didn't want to take the chance of syntax errors
      for (m <- 2 until input) {
        val oldX = priorCell.xCo
        val oldY = priorCell.yCo
        val newCoords = getCoords(m - 1, oldX, oldY)
        val x = newCoords._1
        val y = newCoords._2

        grid(x + 50)(y + 50) = new Cell(x, y, m, (getValue(x, y, grid)))
        priorCell = grid(x + 50)(y + 50)
        if (priorCell.getVal() > input) {
          println("Index: " + m, " Value: " + priorCell.getVal())
          throw new Exception()
        }
      }
    } catch  {
      case _: Throwable => println("Done")
    }
  }
  def getCoords(priorIndex : Int, priorX : Int, priorY : Int) : (Int, Int) = {
    val closestSquare = math.sqrt(priorIndex+1).toInt
    val closestOddSquare = if (closestSquare % 2 == 0) closestSquare -1 else closestSquare
    if (priorIndex+1 == math.pow(closestOddSquare,2)) return (closestOddSquare/2,(closestOddSquare/2)*(-1))
    if (priorX == 0 && priorY == 0) return (priorX+1, priorY)

    val maxCoord : Int = closestOddSquare/2 + 1

    val xMax : Boolean = priorX == maxCoord
    val xMin : Boolean = priorX == maxCoord*(-1)
    val yMax : Boolean = priorY == maxCoord
    val yMin : Boolean = priorY == maxCoord* (-1)

    if (yMin) return (priorX+1,priorY)
    if (xMin) return (priorX,priorY-1)
    if (yMax) return (priorX-1,priorY)
    if (xMax) return (priorX,priorY+1)

    return (priorX+1,priorY)
  }

  def getValue(x : Int, y : Int, grid : Array[Array[Cell]]) : Int = {
    var total = 0
    total += (if (grid(x+51)(y+51) != null) grid(x+51)(y+51).getVal() else 0)
    total += (if (grid(x+50)(y+51) != null) grid(x+50)(y+51).getVal() else 0)
    total += (if (grid(x+49)(y+51) != null) grid(x+49)(y+51).getVal() else 0)
    total += (if (grid(x+51)(y+50) != null) grid(x+51)(y+50).getVal() else 0)
    total += (if (grid(x+51)(y+49) != null) grid(x+51)(y+49).getVal() else 0)
    total += (if (grid(x+49)(y+49) != null) grid(x+49)(y+49).getVal() else 0)
    total += (if (grid(x+49)(y+50) != null) grid(x+49)(y+50).getVal() else 0)
    total += (if (grid(x+50)(y+49) != null) grid(x+50)(y+49).getVal() else 0)
    return total
  }
}

class Cell(x : Int, y : Int, ind : Int, total : Int) {
  val index : Int = ind
  val xCo: Int = x
  val yCo: Int = y
  val value: Int = total

  def getVal(): Int = {
    return value
  }
}
