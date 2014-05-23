package org.kon.game


class Investigator(possessions: List[Possession]) {

  def giveFixedPossessions(fixed: List[Possession], deck: Deck[Possession]): (Investigator, Deck[Possession]) =
    (new Investigator(possessions ::: fixed), deck.draw(fixed))

  def giveRandomPossessions(number: Int, deck: Deck[Possession]): (Investigator, Deck[Possession]) =
    giveFixedPossessions(deck.peek(number), deck)

  def has(p: Possession): Boolean = possessions contains p
}
