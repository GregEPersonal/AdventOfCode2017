//import scala.collection.mutable.ListBuffer
import scala.io.Source

/**
  * Created by gregeusden on 12/4/17.
  */
object SolverDay10 extends App {
  val solver = new Day10P2()
  solver.run()
}

class Day10() {

  //var programs: Array[Disc] = new Array[Disc](1098)

  def run(): Unit = {

    var count: Int = 0
    val filename = "src/main/resources/PromptD10P1.txt"

    var list : Array[Int] = new Array[Int](256)

    for (x <- 0 until list.length) {
      list(x) = x
    }
    var old : Array[Int] = list.clone()
    //var counts : Array[Int] = new Array[Int](1098)

    for (line <- Source.fromFile(filename).getLines) {
      //val name = line.substring(0, line.indexOf(" "))
      val skipLengthsStrings : Array[String] = line.split(",")
      val skipLengths : Array[Int] = new Array[Int](skipLengthsStrings.length)
      for (x <- 0 until skipLengthsStrings.length) {
        skipLengths(x) = skipLengthsStrings(x).toInt
      }
      var skip = 0
      var start = 0

      for (length <- skipLengths) {
        count = 0

        while (count < length) {

          list((start + count) % 256) = old((start + length-count-1) % 256)
          count += 1
        }

        start += skip + length
        skip += 1
        old = list.clone()
        println(start)
      }
      //val weight: Int = line.substring(line.indexOf("(") + 1, line.indexOf(")")).toInt
      //var dependents: ListBuffer[String] = new ListBuffer[String]()
      println(list(0)*list(1))



      //For loop by character
      /*for (char <- line) {
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

      count += 1
      */
    }


  }

}

class Day10P2() {

  var extraLength = 5
  var numRounds = 64

  def run(): Unit = {

    var count: Int = 0
    val filename = "src/main/resources/PromptD10P1.txt"

    var list: Array[Int] = new Array[Int](256)

    for (x <- 0 until list.length) {
      list(x) = x
    }
    var old: Array[Int] = list.clone()
    //var counts : Array[Int] = new Array[Int](1098)

    for (line <- Source.fromFile(filename).getLines) {
      //val name = line.substring(0, line.indexOf(" "))

      val skipLengths: Array[Int] = new Array[Int](line.length() + extraLength)
      var count = 0
      for (char <- line) {
        skipLengths(count) = char.toInt
        count += 1
      }
      skipLengths(count) = 17
      skipLengths(count + 1) = 31
      skipLengths(count + 2) = 73
      skipLengths(count + 3) = 47
      skipLengths(count + 4) = 23


      var skip = 0
      var start = 0
      var roundCount = 0

      while (roundCount < numRounds) {
        for (length <- skipLengths) {
          count = 0

          while (count < length) {

            list((start + count) % 256) = old((start + length - count - 1) % 256)
            count += 1
          }

          start += skip + length
          skip += 1
          old = list.clone()
        }
        roundCount += 1
      }
      val elemPerHash = 16
      val hashValues: Array[Int] = new Array[Int](16)
      for (hashNumber <- 0 until 16) {
        hashValues(hashNumber) = 0
        for (x <- 0 until elemPerHash) {
          hashValues(hashNumber) = hashValues(hashNumber) ^ list(elemPerHash * hashNumber + x)
        }
      }
      var output = ""
      for (x <- hashValues) {
        output = output + toHash(x)
      }

      //val weight: Int = line.substring(line.indexOf("(") + 1, line.indexOf(")")).toInt
      //var dependents: ListBuffer[String] = new ListBuffer[String]()
      println(output)

    }

      //For loop by character
      /*for (char <- line) {
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

      count += 1
      */


  }

  def toHash(num : Int) : String = {
    val rightDigit = num % 16
    val leftDigit = num/16
    var leftString = ""
    var rightString = ""
    if (rightDigit < 10) {
      rightString = rightDigit.toString()
    } else {
      rightString = rightDigit match {
        case 10 => "a"
        case 11 => "b"
        case 12 => "c"
        case 13 => "d"
        case 14 => "e"
        case 15 => "f"
        case _ => ""
      }
    }
    if (leftDigit < 10) {
      leftString = leftDigit.toString()
    } else {
      leftString = leftDigit match {
        case 10 => "a"
        case 11 => "b"
        case 12 => "c"
        case 13 => "d"
        case 14 => "e"
        case 15 => "f"
        case _ => ""
      }
    }
    leftString + rightString
  }

}