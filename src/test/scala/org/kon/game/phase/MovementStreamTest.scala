package org.kon.game.phase

import org.junit.runner.RunWith
import org.kon.game.player.{Investigator, Possession, User}
import org.scalatest.FunSpec
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MovementStreamTest extends FunSpec {
  val investigator: Investigator[Possession] = new Investigator[Possession](1, 2, Nil)
  val mobileUser: User = new User {
    override def wantsToMove: Boolean = true
  }
  val immobileUser: User = new User {
    override def wantsToMove: Boolean = false
  }
  val move: Movement = new Movement(3 :: Nil)

  describe("A movement stream with a single move") {
    val stream: MovementStream = new MovementStream(Stream(move), mobileUser)
    it("allows to read a first move") {
      assert(stream.currentMove === move)
    }
    it("throws an exception when trying to get a next move") {
      intercept[NoSuchElementException] {
        stream.nextMove.currentMove
      }
    }
    it("will answer positively if next move is available") {
      assert(stream.canMove(investigator))
    }

    it("will answer negatively if next move is not available") {
      assert(stream.nextMove.canMove(investigator) === false)
    }

    it("will answer negatively if an investigator ran out of moves") {
      val investigatorWithoutMoves: Investigator[Possession] = new Investigator[Possession](1, 0, Nil)
      assert(stream.canMove(investigatorWithoutMoves) === false)
    }

    it("will answer negatively if a user does not want to move but there are moves available") {
      val stream: MovementStream = new MovementStream(Stream(move), immobileUser)
      assert(stream.canMove(investigator) === false)
    }

  }

  describe("A movement stream with a two moves") {
    val nextMove: Movement = new Movement(7 :: Nil)
    val stream: MovementStream = new MovementStream(Stream(move, nextMove), mobileUser)
    it("allows to read the first move") {
      assert(stream.currentMove === move)
    }

    it("allows to read the second move") {
      assert(stream.nextMove.currentMove === nextMove)
    }

    it("throws an exception when trying to get the third move") {
      intercept[NoSuchElementException] {
        stream.nextMove.nextMove.currentMove
      }
    }
  }

}
