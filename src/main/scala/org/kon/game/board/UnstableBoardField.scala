package org.kon.game.board

class UnstableBoardField(n: Set[BoardField], clues: Int) extends BoardField {
  override def isStable: Boolean = false

  override def neighbors: Set[BoardField] = n

  override def clueCount: Int = clues

  override def addClue(): BoardField = new UnstableBoardField(neighbors, clueCount + 1)

}
