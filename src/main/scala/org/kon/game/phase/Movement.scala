package org.kon.game.phase

import org.kon.game.player.{Investigator, Possession}

class Movement(path: List[Int]) {
  def perform(investigator: Investigator[Possession], board: Board1): Investigator[Possession] =
    if (isValid(investigator, board)) investigator.moveTo(path) else investigator

  def isValid(investigator: Investigator[Possession], board: Board1): Boolean =
    investigator.hasEnoughMovement(path.length) && board.pathIsConnected(investigator.currentPosition, path) && board.noPathObstructions(path)

}
