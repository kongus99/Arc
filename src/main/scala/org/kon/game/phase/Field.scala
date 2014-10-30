package org.kon.game.phase

class Field(neighbors: Set[Int]) {
  def isConnectedTo(field: Int): Boolean = neighbors.contains(field)
}
