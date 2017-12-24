import scala.collection.mutable.ListBuffer
import scala.io.Source

/**
  * Created by gregeusden on 12/4/17.
  */
object SolverDay9 extends App {
  val solver = new Day9()
  solver.run()
}

class Day9() {

  //var programs: Array[Disc] = new Array[Disc](1098)

  def run(): Unit = {

    var count: Int = 0
    val filename = "src/main/resources/PromptD9P1.txt"


    //var counts : Array[Int] = new Array[Int](1098)

    for (line <- Source.fromFile(filename).getLines) {
      //val name = line.substring(0, line.indexOf(" "))

      //val weight: Int = line.substring(line.indexOf("(") + 1, line.indexOf(")")).toInt
      //var dependents: ListBuffer[String] = new ListBuffer[String]()
      var canceled : Boolean = false
      var openBracketCount : Int = 0
      var openTrashCount : Boolean = false
      var score : Int = 0
      var trashCount : Int = 0

      for (char <- line) {
        println(openTrashCount)

        if (openTrashCount) {
          if (!canceled) {
            char match {
              case '!' =>
              case '>' =>
              case _ => trashCount +=1
            }
          }

        }

        if (!canceled) {
          char match {
            case '!' => canceled = true
            case '<' => (openTrashCount = true)
            case '>' => (openTrashCount = false)
            case '{' => if (openTrashCount == false) openBracketCount += 1
            case '}' => if (openTrashCount == false) {
              score += openBracketCount
              openBracketCount -= 1
            }
            case _ =>
          }
        } else {
          canceled = false
        }

      }
      println(score)
      println("Trash: " + trashCount)

      /*if (line.indexOf(">") >= 0) {
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

      }*/


      count += 1
    }


  }

}