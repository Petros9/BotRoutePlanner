import org.scalatest.wordspec.AnyWordSpec

class GridTest extends AnyWordSpec {

  "Grid" when {
    "fillGridWithModules" should {
      "create the correct grid" in new Context {
        grid.fillGridWithModules(modulesNames, 1, fileProcessor)
        assert(grid.topGrid(0)(0).moduleType.equals('H')
          && grid.topGrid(0)(1).moduleType.equals('S')
          && grid.topGrid(1)(0).moduleType.equals('O')
          && grid.topGrid(1)(1).moduleType.equals('H'))
      }
    }

    "getNeighbours" should {
      "return neighbours that can be a part of the path" in new Context {
        grid.fillGridWithModules(modulesNames, 1, fileProcessor)
        assertResult(List(Point(0, 1)))(grid.getNeighbours(Point(1, 1)))
      }
    }

    "findMovementCost" should {
      "get grid dim-sizes" in new Context {
        grid.fillGridWithModules(modulesNames, 1, fileProcessor)
        assertResult(2.0)(grid.findMovementCost(Point(1, 1), List(Point(0, 1))))
      }
    }

  }

    trait Context {

      val x = 2
      val y = 2
      val grid: Grid = Grid(x ,y)

      val fileProcessor: FileProcessor = new FileProcessor
      val gridRowToBeFilled: Int = 0
      val modulesNames: Array[String] = Array("HO", "SH")
  }
}
