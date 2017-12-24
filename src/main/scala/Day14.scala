//import scala.collection.mutable.ListBuffer
import scala.io.Source

/**
  * Created by gregeusden on 12/4/17.
  */
object SolverDay14 extends App {
  val solver = new Day14P2()
  solver.run()
}

class Day14() {

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

class Day14P2() {

  var extraLength = 5
  var numRounds = 64

  def run(): Unit = {

    val input = "uugsqrei"
    val rounds = 128
    var grid = new Array[Array[Int]](rounds)

    for (roundNum <- 0 until rounds) {
      //val name = line.substring(0, line.indexOf(" "))
      val inputAdj = input + "-" + roundNum.toString
      val output = knotHash(inputAdj)
      grid(roundNum) = toBits(output)


    }
    val total = sumFilled(grid)
    val groupGrid = new Array[Array[Int]](128)
    for (y <- 0 until groupGrid.length) {
      groupGrid(y) = new Array[Int](128)
      for (x <- 0 until groupGrid(y).length) {
         groupGrid(y)(x) = 0
      }
    }
    println("Total: " + total)
    var groupCount = 1

    for (y <- 0 until grid.length) {
      for (x <- 0 until grid(y).length) {
        if (grid(y)(x) == 1) {
          var leftNum = -1
          var topNum = -1
          var groupNum1 = 0
          var groupNum2 = 0
          var groupTemp = 0
          if (y > 0) {
            topNum = grid(y-1)(x)
            if (topNum == 1) {
              groupNum1 = groupGrid(y-1)(x)
            }
          }
          if (x > 0) {
            leftNum = grid(y)(x-1)
            if (leftNum == 1) {
              groupNum2 = groupGrid(y)(x-1)
            }
          }
          if (groupNum1 > 0 && groupNum2 > 0) {
            if (groupNum1 < groupNum2 ) {
              editGroup(groupNum1, groupNum2, groupGrid)
              groupTemp = groupNum1
            } else {
              editGroup(groupNum2, groupNum1, groupGrid)
              groupTemp = groupNum2
            }
          } else if (groupNum1 > 0) {
            groupTemp =groupNum1
          } else if (groupNum2 > 0) {
            groupTemp = groupNum2
          } else {
            groupTemp = groupCount
            groupCount += 1
          }
          groupGrid(y)(x) = groupTemp
        }
      }
    }

    printGrid(grid)
    println()
    printGrid(groupGrid)
    println()
    println(countGroups(groupGrid))
  }

  def printGrid(grid : Array[Array[Int]]) : Unit = {
    for (x <- grid) {
      for (y <- x) {
        print(y + ",")
      }
      println()
    }
  }

  def countGroups(grid : Array[Array[Int]]) : Int = {
    val groups = new Array[Int](128*128)

    for (array <- grid) {
      for (x <- array) {
        if (x >=0) {
          groups(x) = 1
        }
      }
    }
    var total = 0
    for (x <- groups) {
      total += x
    }
    total
  }

  def editGroup(a : Int, b : Int, groupGrid : Array[Array[Int]]) : Unit = {
    for (y <- 0 until groupGrid.length) {
      for (x <- 0 until groupGrid(y).length) {
        if (groupGrid(y)(x) == b) {
          groupGrid(y)(x) = a
        }
      }
    }
  }

  def sumFilled(grid : Array[Array[Int]]) : Int = {
    var total = 0
    for (array <- grid) {
      for (value <- array) {
        total += value
      }
    }
    total
  }

  def toBits(input : String) : Array[Int] = {
    var count = 0
    val output = new Array[Int](128)
    for (char <- input) {
      val num = Integer.parseInt(char.toString(),16)
      output(count+3) = num % 2
      output(count+2) = (num >> 1) %2
      output(count+1) = (num >> 2) %2
      output(count) = (num >> 3) %2
      count += 4
    }
    output
  }

  def knotHash(input : String) : String = {

    var list: Array[Int] = new Array[Int](256)

    for (x <- 0 until list.length) {
      list(x) = x
    }
    var old: Array[Int] = list.clone()

    val skipLengths: Array[Int] = new Array[Int](input.length() + extraLength)
    var count = 0
    for (char <- input) {
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
    return output

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