import org.scalatest.wordspec.AnyWordSpec

class FileProcessorTest extends AnyWordSpec {

  "FileProcessor" when {
    "extractGridDataFromTheFirstLine" should {
      "get grid dim-sizes" in new Context {
        assertResult((10, 20, 1))(fileProcessor.extractGridDataFromTheFirstLine(firstLine))
      }
    }

    "prepareProductList" should {
      "prepare a productList" in new Context {
        assertResult(List(Product("P1", 1, 2, 3), Product("P2", 4, 5, 6)))(fileProcessor.prepareProductList(productRows, List()))
      }
    }
  }

  trait Context {

    val firstLine: String = "10 20 1"
    val fileProcessor: FileProcessor = new FileProcessor

    val productRows: Array[String] = Array("P1 1 2 3", "P2 4 5 6")
  }
}
