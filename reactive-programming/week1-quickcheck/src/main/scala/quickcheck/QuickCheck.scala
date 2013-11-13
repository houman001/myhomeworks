package quickcheck

import java.lang.Math.min

import org.scalacheck.Arbitrary
import org.scalacheck.Arbitrary.arbInt
import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.Gen
import org.scalacheck.Gen.frequency
import org.scalacheck.Gen.value
import org.scalacheck.Prop
import org.scalacheck.Prop.forAll
import org.scalacheck.Prop.propBoolean
import org.scalacheck.Properties

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

  property("single-int-element") = forAll { a: Int =>
    val h = insert(a, empty)
    findMin(h) == a
  }

  property("readd-min") = Prop.forAll(genHeap) { h =>
    val m = if (isEmpty(h)) 0 else findMin(h)
    findMin(insert(m, h)) == m
  }

  property("meld-min") = Prop.forAll(genHeap, genHeap) { (h1, h2) =>
    findMin(meld(h1, h2)) == min(findMin(h1), findMin(h2))
  }

  property("sorted") = Prop.forAll(genHeap) { h =>
    heapSorted(h)
  }

  property("meld-and-move") = Prop.forAll(genHeap, genHeap) { (h1, h2) =>
    heapsEqual(meld(h1, h2),
      meld(deleteMin(h1), insert(findMin(h1), h2)))
  }

  def heapSorted(h: H): Boolean =
    if (isEmpty(h)) true
    else {
      val m = findMin(h)
      val rest = deleteMin(h)
      isEmpty(rest) || (m <= findMin(rest) && heapSorted(rest))
    }

  def heapsEqual(h1: H, h2: H): Boolean =
    if (isEmpty(h1) && isEmpty(h2)) true
    else {
      val m1 = findMin(h1)
      val m2 = findMin(h2)
      m1 == m2 && heapsEqual(deleteMin(h1), deleteMin(h2))
    }

  lazy val genHeap: Gen[H] = for {
    n <- arbitrary[A]
    h <- frequency((1, value(empty)), (9, genHeap))
  } yield insert(n, h)

}
