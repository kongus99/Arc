package org.kon.game

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSpec


@RunWith(classOf[JUnitRunner])
class InvestigatorTest extends FunSpec {
  describe("An investigator without possession") {
    val investigator: Investigator[Possession] = new Investigator[Possession](Nil)
    val deck: Deck[Possession] = new Deck[Possession](new TestPossession(1) :: new TestPossession(1) :: new TestPossession(2) :: new TestPossession(3) :: Nil)
    it("should receive fixed possessions from deck, from which the possessions are removed") {
      val result = investigator.giveFixedPossessions(new TestPossession(1) :: new TestPossession(2) :: Nil, deck)
      assert((result._1.has(new TestPossession(1)) &&
        result._1.has(new TestPossession(2)) &&
        !result._1.has(new TestPossession(3))) === true)
    }
  }
}

class TestPossession(v: Int) extends Possession
