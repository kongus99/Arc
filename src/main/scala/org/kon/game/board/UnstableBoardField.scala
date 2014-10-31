package org.kon.game.board

class UnstableBoardField(neighbors: Set[Int], clues: Int) extends BoardField(neighbors, clues) {
  override def isStable: Boolean = false

  override def addClue(): BoardField = new UnstableBoardField(neighbors, clues + 1)

}
