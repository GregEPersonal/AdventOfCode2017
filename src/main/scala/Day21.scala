//import scala.collection.mutable.ListBuffer
import scala.io.Source

/**
  * Created by gregeusden on 12/4/17.
  */
object SolverDay21 extends App {
  val solver = new Day21P1()
  solver.run()
}

class Day14() {

  //var programs: Array[Disc] = new Array[Disc](1098)

  def run(): Unit = {


    val filename = "src/main/resources/PromptD21P1.txt"

    // for (line <- Source.fromFile(filename).getLines) {
    //     // Call a function parseRule(line) that will return a 


    // var list : Array[Int] = new Array[Int](256)

    // for (x <- 0 until list.length) {
    //   list(x) = x
    // }
    // var old : Array[Int] = list.clone()
    // //var counts : Array[Int] = new Array[Int](1098)

    // for (line <- Source.fromFile(filename).getLines) {
    //   //val name = line.substring(0, line.indexOf(" "))
    //   val skipLengthsStrings : Array[String] = line.split(",")
    //   val skipLengths : Array[Int] = new Array[Int](skipLengthsStrings.length)
    //   for (x <- 0 until skipLengthsStrings.length) {
    //     skipLengths(x) = skipLengthsStrings(x).toInt
    //   }
    //   var skip = 0
    //   var start = 0

    //   for (length <- skipLengths) {
    //     count = 0

    //     while (count < length) {

    //       list((start + count) % 256) = old((start + length-count-1) % 256)
    //       count += 1
    //     }

    //     start += skip + length
    //     skip += 1
    //     old = list.clone()
    //     println(start)
    //   }
    //   //val weight: Int = line.substring(line.indexOf("(") + 1, line.indexOf(")")).toInt
    //   //var dependents: ListBuffer[String] = new ListBuffer[String]()
    //   println(list(0)*list(1))



    //   //For loop by character
    //   /*for (char <- line) {
    //     println(openTrashCount)

    //     if (openTrashCount) {
    //       if (!canceled) {
    //         char match {
    //           case '!' =>
    //           case '>' =>
    //           case _ => trashCount +=1
    //         }
    //       }

    //     }

    //     if (!canceled) {
    //       char match {
    //         case '!' => canceled = true
    //         case '<' => (openTrashCount = true)
    //         case '>' => (openTrashCount = false)
    //         case '{' => if (openTrashCount == false) openBracketCount += 1
    //         case '}' => if (openTrashCount == false) {
    //           score += openBracketCount
    //           openBracketCount -= 1
    //         }
    //         case _ =>
    //       }
    //     } else {
    //       canceled = false
    //     }

    //   }
    //   println(score)
    //   println("Trash: " + trashCount)

    //   count += 1
    //   */
    // }


  }

}
