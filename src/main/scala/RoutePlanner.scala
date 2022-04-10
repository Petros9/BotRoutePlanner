import scala.annotation.tailrec
import scala.collection.mutable

class RoutePlanner {

  var calculatedCells: mutable.HashMap[Point, RouteResult] = new mutable.HashMap[Point, RouteResult]()

  @tailrec
  final def shortestPath(grid: Grid, currentPoint: Point, endPoint: Point, visitedPoints: List[Point], nextPlaces: List[Point]): Unit = {
    if (nextPlaces.nonEmpty) {
        val neighbours = grid.getNeighbours(currentPoint).map(neighbour => calculatedCells(neighbour)).min

        calculatedCells(currentPoint) = RouteResult(currentPoint +: neighbours.pointList,
          neighbours.time + grid.findMovementCost(currentPoint, neighbours.pointList))

        val uncheckedPlaces = (nextPlaces ++ grid.getNeighbours(currentPoint)).filter(point => !visitedPoints.contains(point) && calculatedCells(point).time == Double.MaxValue)
      if(uncheckedPlaces.nonEmpty) shortestPath(grid, uncheckedPlaces.head, endPoint, currentPoint +: visitedPoints, uncheckedPlaces.tail)
      }
  }


  def planRouteFromOnePointToTheOther(startingPoint: Point, endingPoint: Point, grid: Grid, x: Int , y: Int): RouteResult = {
    calculatedCells = new mutable.HashMap[Point, RouteResult]()

    for {
      xs <- 0 until x
      ys <- 0 until y
    } yield calculatedCells(Point(xs, ys)) = RouteResult(List(), Double.MaxValue)

    calculatedCells.addOne(startingPoint, RouteResult(List(startingPoint), 0))

    shortestPath(grid, grid.getNeighbours(startingPoint).head, endingPoint, List(), grid.getNeighbours(startingPoint))
    //println(calculatedCells(endingPoint))
    calculatedCells(endingPoint)
  }
}
