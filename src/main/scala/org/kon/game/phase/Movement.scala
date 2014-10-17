package org.kon.game.phase

class Movement(start: Int, path: List[Int]) {
  def isValid(neighbors: SetMap[Int]): Boolean =
    path.foldLeft((start, true): Pair[Int, Boolean])((a: Pair[Int, Boolean], b: Int) => (b, neighbors.contains(a._1, b) && a._2))._2
}
