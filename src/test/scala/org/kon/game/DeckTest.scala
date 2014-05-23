package org.kon.game

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSpec


@RunWith(classOf[JUnitRunner])
class DeckTest extends FunSpec {

  describe("A deck of 3 integers") {
    val deck: Deck[Int] = new Deck(1 :: 2 :: 3 :: Nil)
    it("should return numbers in the given order") {
      assertValuesCorrect(deck, 1 :: 2 :: 3 :: Nil)
    }

    it("should return numbers in the order we put them in order") {
      val deck1 = deck.putAway(7).putAway(4).putAway(9)
      assertValuesCorrect(deck1, 1 :: 2 :: 3 :: 7 :: 4 :: 9 :: Nil)
    }

    it("should throw exception when too many integers are drawn") {
      intercept[NotEnoughDeckElements] {
        deck.drawFirst._2.drawFirst._2.drawFirst._2.drawFirst
      }
    }

    it("should throw exception when you draw an integer not in the deck") {
      intercept[DeckElementNotFound] {
        deck.draw(5)
      }
    }

    it("should throw exception when you draw every integer selectively") {
      intercept[DeckElementNotFound] {
        deck.draw(2)._2.draw(3)._2.draw(1)._2.draw(1)
      }
    }

    it("should return selectively drawn integer") {
      assert(deck.draw(2)._1 === 2)
    }

    it("should remove the integer we have just drawn") {
      intercept[DeckElementNotFound] {
        deck.draw(2)._2.draw(2)
      }
    }

    it("should remove only the first integer we have just drawn") {
      val deck1 = deck.putAway(2).draw(2)._2
      assertValuesCorrect(deck1, 1 :: 3 :: 2 :: Nil)
    }

  }


  def assertValuesCorrect(deck: Deck[Int], elements: List[Int]): Unit = {
    elements match {
      case x :: xs =>
        val first: (Int, Deck[Int]) = deck.drawFirst
        assert(first._1 === x)
        assertValuesCorrect(first._2, xs)
      case Nil =>
    }
  }

}
