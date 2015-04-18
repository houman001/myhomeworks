package quickcheck

import java.lang.Math.min

import org.scalacheck.Arbitrary
import org.scalacheck.Arbitrary.arbContainer
import org.scalacheck.Arbitrary.arbInt
import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.Gen
import org.scalacheck.Gen.const
import org.scalacheck.Gen.frequency
import org.scalacheck.Prop
import org.scalacheck.Prop.forAll
import org.scalacheck.Prop.propBoolean
import org.scalacheck.Properties

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

  property("single-element") = forAll { a: Int =>
    val h = insert(a, empty)
    findMin(h) == a
  }

  property("insert-delete") = forAll { a: Int =>
    val h = insert(a, empty)
    isEmpty(deleteMin(h))
  }

  property("order-doesnt-matter") = forAll { (a: Int, b: Int) =>
    val h1 = insert(b, insert(a, empty))
    val h2 = insert(a, insert(b, empty))
    findMin(h1) == findMin(h2)
  }

  property("two-heaps-melded") = Prop.forAll(genHeap, genHeap) { (h1, h2) =>
    findMin(meld(h1, h2)) == min(findMin(h1), findMin(h2))
  }

  property("sorted-list") = forAll { l: List[Int] =>
    heapEqualsSortedList(makeHeapFromList(l), l.sorted)
  }

  property("compare-two-numbers") = forAll { (a: Int, b: Int) =>
    val h = insert(a, insert(b, empty))
    val n1 = findMin(h)
    val n2 = findMin(deleteMin(h))
    n1 <= n2
  }

  property("sorted") = Prop.forAll(genHeap) { (h) =>
    heapSorted(h)
  }

  property("meld-and-move") = Prop.forAll(genHeap, genHeap) { (h1, h2) =>
    heapsEqual(meld(h1, h2), meld(deleteMin(h1), insert(findMin(h1), h2)))
  }

  def makeHeapFromList(l: List[Int]): H =
    addListToHeap(empty, l)

  def addListToHeap(h: H, l: List[Int]): H = l match {
    case Nil     => h
    case x :: xs => addListToHeap(insert(x, h), xs)
  }

  def heapEqualsSortedList(h: H, l: List[Int]): Boolean = l match {
    case Nil     => isEmpty(h)
    case x :: xs => (x == findMin(h)) && heapEqualsSortedList(deleteMin(h), xs)
  }

  def heapsEqual(h1: H, h2: H): Boolean =
    if (isEmpty(h1) && isEmpty(h2))
      true
    else if (isEmpty(h1) && isEmpty(h2))
      false
    else {
      val m1 = findMin(h1)
      val m2 = findMin(h2)
      m1 == m2 && heapsEqual(deleteMin(h1), deleteMin(h2))
    }

  def heapSorted(h: H): Boolean =
    if (isEmpty(h))
      true
    else {
      val min = findMin(h)
      val rest = deleteMin(h)
      isEmpty(rest) || (min <= findMin(rest) && heapSorted(rest))
    }

  lazy val genHeap: Gen[H] = for {
    n <- arbitrary[Int]
    h <- frequency((1, emptyHeap), (9, genHeap))
  } yield insert(n, h)

  lazy val emptyHeap: Gen[H] = empty

  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)
}
