case class SearchingTime(dependingOnLevel: Int, constant: Int)

case class Module(overrunTime: Double, moduleType: Char, point: Point) extends Ordered[Module]{
  override def compare(otherModule: Module): Int = this.overrunTime.compare(otherModule.overrunTime)

  def takingOutTime(n: Int): Double = {
    moduleType match{
      case 'H' => 3 * n + 4
      case 'B' => 2 * n + 1
      case 'S' => n + 1
    }
  }

}
case class Product(name:String, x: Int, y: Int, n: Int)
case class Point(x: Int, y: Int)

case class RouteResult(pointList: List[Point], time: Double) extends Ordered[RouteResult] {
  override def compare(otherResult: RouteResult): Int = this.time.compare(otherResult.time)
}

