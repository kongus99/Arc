package org.kon.game.phase

import org.kon.game.player.{Investigator, Possession}

class Movement(path: List[Int]) {
  def isValid(investigator: Investigator[Possession], board: Board1[Int]): Boolean =
    investigator.hasEnoughMovement(path) &&
      board.pathIsConnected(investigator.currentPosition, path) &&
      board.noPathObstructions(path)

}
