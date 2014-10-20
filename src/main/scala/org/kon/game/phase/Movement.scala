package org.kon.game.phase

import org.kon.game.player.{Investigator, Possession}

class Movement(investigator: Investigator[Possession], path: List[Int]) {
  def isValid(neighbors: SetMap[Int]): Boolean =
    path.foldLeft((investigator.currentPosition, true): Pair[Int, Boolean])((a: Pair[Int, Boolean], b: Int) => (b, neighbors.contains(a._1, b) && a._2))._2
}
