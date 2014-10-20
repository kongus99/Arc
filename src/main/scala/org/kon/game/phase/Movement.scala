package org.kon.game.phase

import org.kon.game.player.{Investigator, Possession}

class Movement(path: List[Int]) {
  def isValid(investigator: Investigator[Possession], neighbors: SetMap[Int]): Boolean =
    investigator.hasEnoughMovement(path) && pathIsConnected(investigator.currentPosition, neighbors)

  private def pathIsConnected(start: Int, neighbors: SetMap[Int]): Boolean =
    path.foldLeft((start, true): Pair[Int, Boolean])((a: Pair[Int, Boolean], b: Int) => (b, neighbors.contains(a._1, b) && a._2))._2
}
