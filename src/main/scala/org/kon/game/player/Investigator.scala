package org.kon.game.player

import org.kon.game.Deck


class Investigator[T <: Possession](position: Int, movementLimit: Int, possessions: List[T]) {
  val currentPosition: Int = position

  def moveTo(path: List[Int]): Investigator[T] = new Investigator[T](path.last, movementLimit - path.length, possessions)

  def hasEnoughMovement(pathLength: Int): Boolean = pathLength <= movementLimit

  def giveRandomPossessions(number: Int, deck: Deck[T]): (Investigator[T], Deck[T]) =
    giveFixedPossessions(deck peek number, deck)

  def giveFixedPossessions(fixed: List[T], deck: Deck[T]): (Investigator[T], Deck[T]) =
    (new Investigator[T](position, movementLimit, possessions ::: fixed), deck draw fixed)

  def has(p: Possession): Boolean = possessions contains p
}
