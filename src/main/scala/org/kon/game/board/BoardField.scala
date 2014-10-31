package org.kon.game.board

abstract class BoardField(neighbors: Set[Int], clues: Int) {

  def isStable: Boolean

  def hasNeighbor(n: Int): Boolean = neighbors.contains(n)

  def addClue(): BoardField
}
