package recfun
import common._

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int =
    if (c > r || c < 0 || r < 0) 0 else if (c == r || c == 0) 1 else
      pascal(c, r - 1) + pascal(c - 1, r - 1)

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def balance(count: Int, chars: List[Char]): Boolean =
      if (count < 0) false
      else if (chars.isEmpty) (count == 0)
      else balance(count + point(chars.head), chars.tail)
    def point(c: Char): Int =
      if (c == '(') 1 else if (c == ')') -1 else 0
    balance(0, chars)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int =
    if (money < 0) 0
    else if (money == 0) 1
    else if (coins.isEmpty) 0
    else countChange(money, coins.tail) + countChange(money - coins.head, coins)
}
