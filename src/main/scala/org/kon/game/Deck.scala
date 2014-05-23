package org.kon.game

class Deck[C](elements: List[C]) {

  def drawFirst: (C, Deck[C]) = elements match {
    case x :: tail => (x, new Deck(tail))
    case Nil => throw new NotEnoughDeckElements
  }

  def shuffle: Deck[C] = new Deck(util.Random.shuffle(elements))

  def remove(value: C, list: List[C]): List[C] = list diff List(value)

  def draw(elem: C): (C, Deck[C]) = elements match {
    case Nil => throw new NotEnoughDeckElements
    case x :: tail =>
      val found = elements.find(x => x == elem)
      found match {
        case Some(e) => (e, new Deck(remove(e, elements)))
        case None => throw new DeckElementNotFound
      }
  }

  def putAway(elem: C): Deck[C] = new Deck((elem :: elements.reverse).reverse)

}

class NotEnoughDeckElements extends RuntimeException

class DeckElementNotFound extends RuntimeException
