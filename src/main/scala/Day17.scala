/**
  * Created by gregeusden on 12/4/17.
  */
object SolverDay17 extends App {
  val solver = new Day17()
  solver.run()
}

class Day17() {

  def run(): Unit = {

    var count: Int = 1
    val insertSteps = 316

    var circBuffer = List(0,1)

    var startingIndex = 1

    for (x <- 2 to 2017) {
      val nextIndex = (startingIndex + (insertSteps % circBuffer.length)) % circBuffer.length
      val lists = circBuffer.splitAt(nextIndex+1)
      circBuffer = (lists._1 :+ x) ::: lists._2
      startingIndex = nextIndex+1
      if (x % 10000 == 0) println("X is: " + x)
    }
    println("Index is: " + circBuffer.indexOf(2017))
    println("Result: " + circBuffer(circBuffer.indexOf(2017)+1))

    //PART TWO: Just determine how often it lands on zero, and record the number when it does. Forget the rest of hte list.

    var zeroIndex = circBuffer.indexOf(0)
    var result = circBuffer(zeroIndex+1)
    var bufferLength = circBuffer.length
    for (x <- 2018 to 50000000) {
      val nextIndex = (startingIndex + (insertSteps % bufferLength)) % bufferLength
      if (nextIndex == zeroIndex) {
        result = x
      } else if (nextIndex < zeroIndex) {
        zeroIndex += 1
      }
      startingIndex = nextIndex+1
      bufferLength += 1
    }
    println("Result is: " + result)
  }
}
