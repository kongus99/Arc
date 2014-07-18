package org.kon.game.board

trait BoardField {
  def clueCount: Int

  def neighbors: Set[BoardField]

  def isStable: Boolean

  def hasNeighbor(n: BoardField): Boolean = neighbors.contains(n)

  def addClue(): BoardField
}
