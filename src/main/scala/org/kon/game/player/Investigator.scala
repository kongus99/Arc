package org.kon.game.player

import org.kon.game.Deck


class Investigator[T <: Possession](position: Int, possessions: List[T]) {
  val currentPosition: Int = position

  def giveRandomPossessions(number: Int, deck: Deck[T]): (Investigator[T], Deck[T]) =
    giveFixedPossessions(deck peek number, deck)

  def giveFixedPossessions(fixed: List[T], deck: Deck[T]): (Investigator[T], Deck[T]) =
    (new Investigator[T](position, possessions ::: fixed), deck draw fixed)

  def has(p: Possession): Boolean = possessions contains p
}
