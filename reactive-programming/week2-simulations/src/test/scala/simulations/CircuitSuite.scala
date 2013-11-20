package simulations

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.Checkers

@RunWith(classOf[JUnitRunner])
class CircuitSuite extends CircuitSimulator with FunSuite with Checkers {
  val InverterDelay = 1
  val AndGateDelay = 3
  val OrGateDelay = 5
  
  test("andGate example") {
    val in1, in2, out = new Wire
    andGate(in1, in2, out)
    in1.setSignal(false)
    in2.setSignal(false)
    run
    
    assert(out.getSignal === false, "and 1")

    in1.setSignal(true)
    run
    
    assert(out.getSignal === false, "and 2")

    in2.setSignal(true)
    run
    
    assert(out.getSignal === true, "and 3")
  }

  test("orGate example") {
    val in1, in2, out = new Wire
    orGate(in1, in2, out)
    in1.setSignal(false)
    in2.setSignal(false)
    run
    
    assert(out.getSignal === false, "or 1")

    in1.setSignal(true)
    run
    
    assert(out.getSignal === true, "or 2")

    in2.setSignal(true)
    run
    
    assert(out.getSignal === true, "or 3")
  }


  test("orGate2 example") {
    val in1, in2, out = new Wire
    orGate2(in1, in2, out)
    in1.setSignal(false)
    in2.setSignal(false)
    run
    
    assert(out.getSignal === false, "or2 1")

    in1.setSignal(true)
    run
    
    assert(out.getSignal === true, "or2 2")

    in2.setSignal(true)
    run
    
    assert(out.getSignal === true, "or2 3")
  }

  test("demux-simple") {
    val in, c0, c1, o0, o1, o2, o3 = new Wire
    demux(in, List(c1,c0), List(o3,o2,o1,o0))
    in.setSignal(false)
    c0.setSignal(false)
    c1.setSignal(false)
    run    
    assert(o0.getSignal == false, "demux2 1")
    assert(o1.getSignal == false, "demux2 1")
    assert(o2.getSignal == false, "demux2 1")
    assert(o3.getSignal == false, "demux2 1")
    in.setSignal(true)
    run
    assert(o0.getSignal == true, "demux2 2")
    assert(o1.getSignal == false, "demux2 2")
    assert(o2.getSignal == false, "demux2 2")
    assert(o3.getSignal == false, "demux2 2")
    c1.setSignal(true)
    run
    assert(o0.getSignal == false, "demux2 3")
    assert(o1.getSignal == false, "demux2 3")
    assert(o2.getSignal == true, "demux2 3")
    assert(o3.getSignal == false, "demux2 3")
    c1.setSignal(false)
    c0.setSignal(true)
    run
    assert(o0.getSignal == false, "demux2 4")
    assert(o1.getSignal == true, "demux2 4")
    assert(o2.getSignal == false, "demux2 4")
    assert(o3.getSignal == false, "demux2 4")    
    c1.setSignal(true)
    c0.setSignal(true)
    run
    assert(o0.getSignal == false, "demux2 5")
    assert(o1.getSignal == false, "demux2 5")
    assert(o2.getSignal ==false, "demux2 5")
    assert(o3.getSignal == true, "demux2 5") 
  }

  test("Demux") {
    check(new QuickCircuit)
  }
}
