package org.kon.game.board

import org.kon.game.Deck
import org.kon.game.board.BoardElements.Gate

class Board(fields: Set[BoardField], gates: Deck[Gate]) {
  var terrorLevel: Int = 0
  var gateState: Deck[Gate] = gates.shuffle
  var fieldState: Set[BoardField] = fields.map(x => if (x.isStable) x else x.addClue())

  def increaseTerrorLevel(): Unit = terrorLevel += 1

  def decreaseTerrorLevel(): Unit = if (terrorLevel > 0) terrorLevel -= 1

  def canMove(from: BoardField, to: BoardField): Boolean = from.hasNeighbor(to)

}
