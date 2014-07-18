package org.kon.game.board

abstract class BoardField(neighbors: Set[BoardField], clues: Int) {

  def isStable: Boolean

  def hasNeighbor(n: BoardField): Boolean = neighbors.contains(n)

  def addClue(): BoardField
}
