import scala.annotation.tailrec

object Grid {
  def apply(x: Int, y: Int): Grid = new Grid(x, y)
}

class Grid(x: Int, y: Int) {
  var topGrid: Array[Array[Module]] = Array.ofDim[Module](x, y)

  @tailrec
  private def fillFileRow(columnNumber: Int, rowNumber: Int, characters: List[Char]): Unit = {
    if(!x.equals(columnNumber)){
      characters.head match {
        case 'H' => topGrid(columnNumber)(rowNumber) = Module(0.5, 'H', Point(columnNumber, rowNumber))
        case 'B' => topGrid(columnNumber)(rowNumber) = Module(1, 'B', Point(columnNumber, rowNumber))
        case 'S' => topGrid(columnNumber)(rowNumber) = Module(2, 'S', Point(columnNumber, rowNumber))
        case 'O' => topGrid(columnNumber)(rowNumber) = Module(Double.MaxValue, 'O', Point(columnNumber, rowNumber))
      }
      fillFileRow(columnNumber + 1, rowNumber, characters.tail)
    }
  }

  def fillGridWithModules(fileText: Array[String], processingRow: Int, fileProcessor: FileProcessor): List[Product] = {
    if(!processingRow.equals(y + 1)) {
      fillFileRow(0, processingRow - 1, fileText.head.toList)
      fillGridWithModules(fileText.tail, processingRow + 1, fileProcessor)
    }
    else fileProcessor.prepareProductList(fileText, List())
  }

  def getNeighbours(point: Point): List[Point] = {
    val potentialList: List[Point] = List(Point(point.x + 1, point.y), Point(point.x - 1, point.y), Point(point.x, point.y + 1), Point(point.x, point.y - 1))
    potentialList.filter(point => point.x >= 0 && point.y >= 0 && point.x < this.x && point.y < this.y && !topGrid(point.x)(point.y).moduleType.equals('O'))
  }

  def findMovementCost(cell1: Point, previousVisitedPoints: List[Point]): Double = {
    if(previousVisitedPoints.isEmpty) 0
    else Math.max(topGrid(cell1.x)(cell1.y).overrunTime, topGrid(previousVisitedPoints.head.x)(previousVisitedPoints.head.y).overrunTime)
  }
}