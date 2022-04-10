import scala.annotation.tailrec

class FileProcessor {

  def extractGridDataFromTheFirstLine(firstLine: String): (Int, Int, Int) = {
    val sizes: Array[String] = firstLine.split(' ')
    val firstDim: Int = sizes(0).toInt
    val secondDim: Int = sizes(0).toInt
    val thirdDim: Int = sizes(0).toInt
    if(firstDim < 0 || secondDim < 0 || thirdDim < 0 ) throw new IllegalArgumentException
    else (sizes(0).toInt, sizes(1).toInt, sizes(2).toInt)
  }

  @tailrec
  final def prepareProductList(textRows: Array[String], resultProductList: List[Product]): List[Product] = {
    if(textRows.nonEmpty) {
      val values: Array[String] = textRows.head.split(' ')
      prepareProductList(textRows.tail, resultProductList :+ Product(values(0), values(1).toInt, values(2).toInt, values(3).toInt))
    }
    else resultProductList
  }

  def extractTaskData(fileText: Array[String]): (Point, Point, String) = {
    val firstLineElements: Array[String] = fileText(0).split(' ')
    val secondLineElements: Array[String] = fileText(1).split(' ')
    (Point(firstLineElements(0).toInt, firstLineElements(1).toInt), Point(secondLineElements(0).toInt, secondLineElements(1).toInt), fileText(2))
  }


  def writeResult(movementTime: Double, takingOutTime: Double, path: List[Point]): Unit = {
    os.write.append(Conf.filesPaths / Conf.resultFileName, movementTime.toString + "\n")
    os.write.append(Conf.filesPaths / Conf.resultFileName, (movementTime + takingOutTime).toString + "\n")
    path.foreach(point =>
      os.write.append(Conf.filesPaths / Conf.resultFileName, point.x.toString + ' ' + point.y.toString + "\n"))
  }
}
