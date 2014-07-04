package org.kon.game.player

import org.kon.game.Deck


class Investigator[T <: Possession](possessions: List[T]) {

  def giveFixedPossessions(fixed: List[T], deck: Deck[T]): (Investigator[T], Deck[T]) =
    (new Investigator[T](possessions ::: fixed), deck draw fixed)

  def giveRandomPossessions(number: Int, deck: Deck[T]): (Investigator[T], Deck[T]) =
    giveFixedPossessions(deck peek number, deck)

  def has(p: Possession): Boolean = possessions contains p
}
