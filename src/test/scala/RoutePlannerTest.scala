import org.scalatest.wordspec.AnyWordSpec

class RoutePlannerTest extends AnyWordSpec {

  "RoutePlanner" when {
    "shortestPath" should {
      "find the shortest path" in new Context {
        val resultPath: RouteResult = routePlanner.planRouteFromOnePointToTheOther(Point(0, 0), Point(2, 2), grid, x, y)
        println(resultPath)
        assert(resultPath.time.equals(3.5) && resultPath.pointList.equals(List(Point(0,0), Point(0,1), Point(1,1), Point(1,2), Point(2,2)).reverse))
      }
    }
  }

  trait Context {
    val x = 3
    val y = 3
    val grid: Grid = Grid(x ,y)

    val fileProcessor: FileProcessor = new FileProcessor
    val gridRowToBeFilled: Int = 0
    val modulesNames: Array[String] = Array("HOH", "HHS", "SHS")
    grid.fillGridWithModules(modulesNames, 1, fileProcessor)

    val routePlanner: RoutePlanner = new RoutePlanner
  }
}
//"HOH"
//"HHS"
//"SHS"