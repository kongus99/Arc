package org.kon.game

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSpec


@RunWith(classOf[JUnitRunner])
class DeckTest extends FunSpec with DeckTester {

  describe("A deck of 3 integers") {
    val deck: Deck[Int] = new Deck(1 :: 2 :: 3 :: Nil)
    it("should return numbers in the given order") {
      deckEquals(deck, 1 :: 2 :: 3 :: Nil)
    }

    it("should return numbers in the order we put them in order") {
      val deck1 = deck.putAway(7).putAway(4).putAway(9)
      deckEquals(deck1, 1 :: 2 :: 3 :: 7 :: 4 :: 9 :: Nil)
    }

    it("should throw exception when too many integers are drawn") {
      intercept[NotEnoughDeckElements] {
        deck.drawFirst._2.drawFirst._2.drawFirst._2.drawFirst
      }
    }

    it("should throw exception when you draw an integer not in the deck") {
      intercept[DeckElementNotFound] {
        deck.draw(List(5))
      }
    }

    it("should throw exception when you draw every integer selectively") {
      intercept[DeckElementNotFound] {
        deck.draw(List(2)).draw(List(3)).draw(List(1)).draw(List(1))
      }
    }

    it("should remove the integer we have just drawn") {
      intercept[DeckElementNotFound] {
        deck.draw(List(2)).draw(List(2))
      }
    }

    it("should remove only the first integer we have just drawn") {
      val deck1 = deck.putAway(2).draw(List(2))
      deckEquals(deck1, 1 :: 3 :: 2 :: Nil)
    }
    it("should not allow to remove same integer multiple times if it occurs only once") {
      intercept[DeckElementNotFound] {
        deck.draw(List(2, 2))
      }
    }

    it("should remove all occurrences of repeating integers") {
      val deck1 = deck.putAway(2).putAway(3).putAway(2).draw(List(2, 2, 2))
      intercept[DeckElementNotFound] {
        deck1.draw(List(2))
      }
    }

    it("should remove no more than given number of integers") {
      val deck1 = deck.putAway(2).draw(List(2))
      deck1.draw(List(2))
    }

    it("should not remove if empty list is given") {
      val deck1 = deck.draw(Nil)
      deckEquals(deck1, 1 :: 2 :: 3 :: Nil)
    }

    it("should allow us to peek up to its size") {
      val peek = deck.peek(3)
      assert(peek === 1 :: 2 :: 3 :: Nil)
    }

    it("should not allow us to peek above its size") {
      intercept[NotEnoughDeckElements] {
        deck.peek(4)
      }
    }
  }
}

trait DeckTester extends FunSpec {
  def deckEquals[D](deck: Deck[D], elements: List[D]): Unit = {
    elements match {
      case x :: xs =>
        val first: (D, Deck[D]) = deck.drawFirst
        assert(first._1 === x)
        deckEquals(first._2, xs)
      case Nil => assert(deck.size === 0)
    }
  }
}
