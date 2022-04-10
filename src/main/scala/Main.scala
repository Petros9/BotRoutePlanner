object Main {

  def main(args : Array[String]): Unit = {

    // ### GRID FILE MODEL PREPARATION ###
    val gridFileName = args(0)
    val gridFileText = os.read(Conf.filesPaths / gridFileName)

    val gridFileRows = gridFileText.split('\n')

    val fileProcessor = new FileProcessor

    val routePlanner = new RoutePlanner
    // process the first line
    try {
      val (x, y, n) = fileProcessor.extractGridDataFromTheFirstLine(gridFileRows.head)
      // initialise and fill the array
      val grid = Grid(x, y)
      val productList: List[Product] = grid.fillGridWithModules(gridFileRows.tail, 1, fileProcessor)
      // ### TASK FILE MODEL PREPARATION ###
      val taskFileName = args(1)
      val taskFileText = os.read(Conf.filesPaths / taskFileName)

      val (startPoint, endPoint, productName) = fileProcessor.extractTaskData(taskFileText.split('\n'))

      val productThatIsLookedFor: Product = productList.filter(product => product.name.equals(productName)).head


      // ### ROUTE PLANNING ###

      val routeFromTheStartingPointToTheProduct = routePlanner.planRouteFromOnePointToTheOther(startPoint, Point(productThatIsLookedFor.x, productThatIsLookedFor.y), grid, x, y)
      val routeFromTheProductToTheEndingPoint = routePlanner.planRouteFromOnePointToTheOther(Point(productThatIsLookedFor.x, productThatIsLookedFor.y), endPoint, grid, x, y)
      val takingOutTime: Double = grid.topGrid(productThatIsLookedFor.x)(productThatIsLookedFor.y).takingOutTime(productThatIsLookedFor.n)

      val finalResult = RouteResult(routeFromTheStartingPointToTheProduct.pointList.reverse ++ routeFromTheProductToTheEndingPoint.pointList.reverse, routeFromTheStartingPointToTheProduct.time + routeFromTheProductToTheEndingPoint.time)

      println(finalResult)
      // ### CREATING THE RESULT ###
      fileProcessor.writeResult(finalResult.time, takingOutTime, finalResult.pointList)

    } catch {
      case _:IllegalArgumentException => println("Parameter files are not correct")
    }
  }
}
