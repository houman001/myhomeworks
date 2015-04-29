package calculator

object Polynomial {
  def computeDelta(a: Signal[Double], b: Signal[Double],
      c: Signal[Double]): Signal[Double] = {
    Signal(b() * b() - 4 * a() * c())
  }

  def computeSolutions(a: Signal[Double], b: Signal[Double],
      c: Signal[Double], delta: Signal[Double]): Signal[Set[Double]] = {
    Signal(computeSolution(a(), b(), c(), delta()))
  }
  
  def computeSolution(a: Double, b: Double, c: Double, delta: Double): Set[Double] = {
    if (delta < 0)
      Set()
    else {
      Set(math.sqrt(delta), -math.sqrt(delta)) map (x => (-b + x) / (2 * a))
    }
  }
}
