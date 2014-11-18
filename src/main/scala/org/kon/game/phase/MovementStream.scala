package org.kon.game.phase

import org.kon.game.player.{Investigator, Possession, User}

class MovementStream(movements: Stream[Movement], u: User) {
  def head: Movement = movements.head

  def nextMove: MovementStream = new MovementStream(movements.drop(1), u)

  def canMove(i: Investigator[Possession]): Boolean = u.wantsToMove && i.canMove && movements.isEmpty

}
