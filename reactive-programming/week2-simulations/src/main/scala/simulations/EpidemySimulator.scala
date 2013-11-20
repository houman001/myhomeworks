package simulations

import math.random

class EpidemySimulator extends Simulator {

  def intRandomBelow(i: Int) = (random * i).toInt

  protected[simulations] object SimConfig {
    val population: Int = 300
    val roomRows: Int = 8
    val roomColumns: Int = 8

    val incubationTime = 6
    val dieTime = 14
    val immuneTime = 16
    val healTime = 18
    val prevalenceRate = 0.01
    val transRate = 0.4
    val dieRate = 0.25
  }

  import SimConfig._

  val persons: List[Person] = (0 until population).toList.map( i =>  
    {
      val p = new Person(i)
      if (i < population * prevalenceRate)
          p.setInfected()
      p.mode()
      p
    }
  )

  class Person(val id: Int) {
    var infected = false
    var sick = false
    var immune = false
    var dead = false

    var row: Int = intRandomBelow(roomRows)
    var col: Int = intRandomBelow(roomColumns)

    def setInfected() {
      infected = true
      afterDelay(incubationTime)(setSick)
      afterDelay(dieTime)(setDead)
      afterDelay(immuneTime)(setImmune)
      afterDelay(healTime)(setHealthy)
    }

    def setSick() {
      sick = true
    }

    def setDead() {
      if (random < dieRate)
        dead = true
    }

    def setImmune() {
      if (dead)
        return
      sick = false
      immune = true
    }

    def setHealthy() {
      if (dead)
        return
      immune = false
      infected = false
    }

    def mode() {
      val moveDelay = intRandomBelow(5) + 1
      afterDelay(moveDelay)(move)
    }

    def move() {
      if (dead)
        return
      val neighbors = List(((row - 1 + roomRows) % roomRows, col),
        ((row + 1) % roomRows, col),
        (row, (col - 1 + roomColumns) % roomColumns),
        (row, (col + 1) % roomColumns))
      def isHealthy(room: (Int, Int)): Boolean = room match {
        case (r, c) => (persons.find { p => p.row == r && p.col == c && (p.sick || p.dead) }).isEmpty
      }
      val candidates = neighbors filter isHealthy
      if (!candidates.isEmpty) {
        val candidate: (Int, Int) = candidates(intRandomBelow(candidates.length))
        candidate match {
          case (a, b) => row = a; col = b
        }
      }
      if (!immune && !infected)
        if (random < transRate)
          if (!(persons.find { p => p.row == row && p.col == col && p.infected }).isEmpty)
            setInfected()
      mode()
    }
  }
}
