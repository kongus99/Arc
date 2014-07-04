package org.kon.game

import org.junit.runner.RunWith
import org.kon.game.player.{Investigator, Possession}
import org.scalatest.FunSpec
import org.scalatest.junit.JUnitRunner


@RunWith(classOf[JUnitRunner])
class InvestigatorTest extends FunSpec with DeckTester {
  describe("An investigator without possession") {
    val investigator: Investigator[Possession] = new Investigator[Possession](Nil)
    val deck: Deck[Possession] = new Deck[Possession](new TP(1) :: new TP(2) :: new TP(3) :: new TP(1) :: Nil)

    it("should receive fixed possessions from deck, from which the possessions are removed") {
      val result = investigator.giveFixedPossessions(new TP(1) :: new TP(2) :: Nil, deck)
      val i = result._1
      val d = result._2
      assert((i.has(new TP(1)) && i.has(new TP(2)) && !i.has(new TP(3))) === true)
      deckEquals(d, new TP(3) :: new TP(1) :: Nil)
    }
    it("should receive random possessions from top of the deck") {
      val result = investigator.giveRandomPossessions(2, deck)
      val i = result._1
      val d = result._2
      assert((i.has(new TP(1)) && i.has(new TP(2)) && !i.has(new TP(3))) === true)
      deckEquals(d, new TP(3) :: new TP(1) :: Nil)
    }

  }
}

class TP(val v: Int) extends Possession {
  override def equals(other: Any): Boolean = other match {
    case that: TP => this.v == that.v
    case _ => false
  }

  override def hashCode: Int = 31 + v
}
