package org.kon.game

class Deck[C](elements: List[C]) {

  def drawFirst: (C, Deck[C]) = elements match {
    case x :: tail => (x, new Deck(tail))
    case Nil => throw new NotEnoughDeckElements
  }

  def shuffle: Deck[C] = new Deck(util.Random.shuffle(elements))

  def draw(elem: C): (C, Deck[C]) = elements match {
    case Nil => throw new DeckElementNotFound
    case x :: tail =>
      val found = elements.find(x => x == elem)
      found match {
        case Some(e) => (e, new Deck(elements diff List(e)))
        case None => throw new DeckElementNotFound
      }
  }

  def putAway(elem: C): Deck[C] = new Deck((elem :: elements.reverse).reverse)

}

class NotEnoughDeckElements extends RuntimeException

class DeckElementNotFound extends RuntimeException
