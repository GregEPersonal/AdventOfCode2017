import scala.collection.mutable.ListBuffer
import scala.io.Source

/**
  * Created by gregeusden on 12/4/17.
  */
object SolverDay7 extends App {
  val solver = new Day7()
  solver.run()
}

class Day7() {

  var programs: Array[Disc] = new Array[Disc](1098)

  def run(): Unit = {

    var count: Int = 0
    val filename = "src/main/resources/PromptD7P1.txt"


    //var counts : Array[Int] = new Array[Int](1098)

    for (line <- Source.fromFile(filename).getLines) {
      val name = line.substring(0, line.indexOf(" "))

      val weight: Int = line.substring(line.indexOf("(") + 1, line.indexOf(")")).toInt
      var dependents: ListBuffer[String] = new ListBuffer[String]()

      if (line.indexOf(">") >= 0) {
        var names = line.substring(line.indexOf(">") + 1)
        var moreNames: Boolean = true
        var currentIndex: Int = 0
        while (moreNames) {
          if (names.indexOf(",") >= 0) {
            val firstName = names.substring(1, names.indexOf(","))
            dependents += firstName

            names = names.substring(names.indexOf(",") + 1)
          } else {
            val lastName = names.substring(1)

            dependents += lastName

            moreNames = false
          }
        }

      }
      val tower = new Disc(name, weight, dependents.toList)
      programs(count) = tower
      count += 1
    }

    val base = getBase(programs)
    getWeight(base)

    println("Complete")
  }

  def getBase(list : Array[Disc]): Disc = {
    var counts = new Array[Int](list.length)
    var index = 0
    for(x <- list ) {
      val children = x.children
      for (c <- children) {
        for (d <- 0 until list.length) {
          if (c.toString == list(d).toString()) {
            counts(d) += 1
          }
        }
      }
    }
    for (a <- 0 until counts.length) {
      if (counts(a) == 0) { return list(a)}
    }
    return list(0)
  }

  def getWeight(tower : Disc): Int = {
    if (tower.getChildren().nonEmpty) {
      var childList: Array[Disc] = new Array[Disc](tower.getChildren().length)
      var weightList : Array[Int] = new Array[Int](tower.getChildren().length)
      var index = 0
      for (a <- tower.getChildren()) {
        val tmp : Disc = getDisc(a)
        childList(index) =  tmp
        index += 1
      }
      var totalChildWeight = 0
      var weightIndex = 0
      for (a <- childList) {
        weightList(weightIndex) = getWeight(a)
        totalChildWeight += weightList(weightIndex)
        weightIndex += 1
      }
      var weightsMatch : Boolean = true
      for (x <- 1 until weightList.length) {
        if (weightList(x-1) != weightList(x)) {
          weightsMatch = false
        }
      }
      if (!weightsMatch) {
        println("Wrong weight: " + (tower.personalWeight() + totalChildWeight) + " Name: " +tower.toString())
        var index2 : Int = 0
        for (a <- childList) {
          println("    Child: " + (weightList(index2)) + " Name: " +a.toString())
          index2 += 1
        }
      }
      return tower.personalWeight() + totalChildWeight

    } else {
      return tower.personalWeight()
    }

  }

  def getDisc(name : String) : Disc = {
    for (a <- programs) {
      if (a.toString() == name) {
        return a
      }
    }
    return programs(0)
  }
}

class Disc(nme : String, wght : Int, towers : List[String]) {
  val name = nme
  val weight = wght
  val children = towers

  override def toString() : String = {
     name
  }
  def personalWeight() : Int = {
    weight
  }

  def getChildren() : List[String] = {
    children
  }
}