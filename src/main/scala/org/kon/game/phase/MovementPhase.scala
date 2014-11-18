package org.kon.game.phase

import org.kon.game.player.{Investigator, Possession}

class MovementPhase(board: Board1) {

  //  private def performValidMove(m: Movement, i: Investigator[Possession]): Investigator[Possession] =
  //    if (m.isValid(i, board)) m.perform(i, board)
  //    else i

  def move(investigator: Investigator[Possession], s: MovementStream): Investigator[Possession] = {
    investigator
    //    val i: Investigator[Possession] = performValidMove(s.head, investigator)
    //    if (!s.canMove(i)) i
    //    else move(i, s.nextMove)
  }
}

