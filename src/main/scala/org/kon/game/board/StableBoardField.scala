package org.kon.game.board

class StableBoardField(n: Set[BoardField], clues: Int) extends BoardField {
  override def isStable: Boolean = true

  override def neighbors: Set[BoardField] = n

  override def clueCount: Int = clues

  override def addClue(): BoardField = new StableBoardField(neighbors, clueCount + 1)
}
