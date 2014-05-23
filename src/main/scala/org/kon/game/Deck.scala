package org.kon.game

class Deck[C](elements: List[C]) {

  def drawFirst: (C, Deck[C]) = elements match {
    case x :: tail => (x, new Deck(tail))
    case Nil => throw new NotEnoughDeckElements
  }

  def shuffle: Deck[C] = new Deck(util.Random.shuffle(elements))

  def draw(elems: List[C]): Deck[C] = {
    val subtracted = elements diff elems
    if (elements.size - subtracted.size != elems.size)
      throw new DeckElementNotFound
    new Deck[C](subtracted)
  }

  def putAway(elem: C): Deck[C] = new Deck((elem :: elements.reverse).reverse)

  def peek(number: Int): List[C] = innerPeek(number, this, Nil)

  private def innerPeek(number: Int, deck: Deck[C], acc: List[C]): List[C] = number match {
    case 0 => acc.reverse
    case x =>
      val first = deck.drawFirst
      innerPeek(x - 1, first._2, first._1 :: acc)
  }

}

class NotEnoughDeckElements extends RuntimeException

class DeckElementNotFound extends RuntimeException
