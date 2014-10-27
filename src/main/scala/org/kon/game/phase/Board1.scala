package org.kon.game.phase

class Board1[T](neighbors: SetMap[T], obstructions: List[T]) {

  def noPathObstructions(path: List[T]): Boolean = !containsObstructions(path)

  def pathIsConnected(start: T, path: List[T]): Boolean =
    path.foldLeft((start, true): Pair[T, Boolean])((a: Pair[T, Boolean], b: T) => (b, neighbors.contains(a._1, b) && a._2))._2

  private def containsObstructions(path: List[T]): Boolean = path match {
    case Nil => false
    case h :: tail => obstructions.contains(h) || containsObstructions(tail)
  }

}
