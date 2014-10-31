package org.kon.game.board

class StableBoardField(neighbors: Set[Int], clues: Int) extends BoardField(neighbors, clues) {
  override def isStable: Boolean = true

  override def addClue(): BoardField = new StableBoardField(neighbors, clues + 1)
}
