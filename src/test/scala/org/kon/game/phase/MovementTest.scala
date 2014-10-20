package org.kon.game.phase

import org.junit.runner.RunWith
import org.kon.game.player.{Investigator, Possession}
import org.scalatest.FunSpec
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MovementTest extends FunSpec {
  val investigator: Investigator[Possession] = new Investigator[Possession](1, 10, Nil)
  describe("A simple adjacent move") {

    val move: Movement = new Movement(3 :: Nil)

    it("is valid when fields are adjacent") {
      assert(move.isValid(investigator, new SetMap((1, 3) ::(3, 1) :: Nil)))
    }
    it("is not valid when fields are not directly adjacent") {
      assert(move.isValid(investigator, new SetMap((1, 2) ::(2, 1) ::(2, 3) ::(3, 2) :: Nil)) === false)
    }
    it("is valid when fields are adjacent and there is second neighbor") {
      assert(move.isValid(investigator, new SetMap((1, 3) ::(1, 2) ::(3, 1) ::(2, 1) :: Nil)))
    }
    it("is valid when there is only reverse adjacency provided") {
      assert(move.isValid(investigator, new SetMap((3, 1) :: Nil)))
    }
  }

  describe("A back and forth move") {
    val move: Movement = new Movement(3 :: 1 :: Nil)

    it("is valid when fields are adjacent") {
      assert(move.isValid(investigator, new SetMap((1, 3) :: Nil)))
    }
    it("is not valid when fields are not directly adjacent") {
      assert(move.isValid(investigator, new SetMap((1, 2) ::(2, 1) ::(2, 3) ::(3, 2) :: Nil)) === false)
    }

  }

  describe("A two field move") {
    val move: Movement = new Movement(3 :: 2 :: Nil)

    it("is valid when there is a path between fields") {
      assert(move.isValid(investigator, new SetMap((1, 3) ::(2, 3) :: Nil)))
    }

    it("is not valid when there is a missing second step") {
      assert(move.isValid(investigator, new SetMap((1, 3) :: Nil)) === false)
    }

  }

  describe("A three field move") {
    val move: Movement = new Movement(3 :: 2 :: 4 :: Nil)

    it("is valid when there is a path between fields") {
      assert(move.isValid(investigator, new SetMap((1, 3) ::(2, 3) ::(4, 2) :: Nil)))
    }

    it("is not valid when there is a missing first step") {
      assert(move.isValid(investigator, new SetMap((2, 3) ::(4, 2) :: Nil)) === false)
    }

    it("is not valid when there is a missing second step") {
      assert(move.isValid(investigator, new SetMap((4, 2) ::(1, 3) :: Nil)) === false)
    }

    it("is not valid when there is a missing third step") {
      assert(move.isValid(investigator, new SetMap((3, 1) ::(3, 2) :: Nil)) === false)
    }

  }

  describe("A three field loop move") {
    val move: Movement = new Movement(3 :: 2 :: 1 :: Nil)

    it("is valid when there is a path between fields") {
      assert(move.isValid(investigator, new SetMap((1, 3) ::(2, 3) ::(1, 2) :: Nil)))
    }

  }

  describe("A move that is two field long") {
    val move: Movement = new Movement(3 :: 2 :: Nil)

    it("should not be valid if player has only one move point") {
      val investigator: Investigator[Possession] = new Investigator[Possession](1, 1, Nil)
      assert(move.isValid(investigator, new SetMap((1, 3) ::(2, 3) ::(1, 2) :: Nil)) === false)
    }

    it("should be valid if player has exactly two move point") {
      val investigator: Investigator[Possession] = new Investigator[Possession](1, 2, Nil)
      assert(move.isValid(investigator, new SetMap((1, 3) ::(2, 3) ::(1, 2) :: Nil)))
    }

    it("should be valid if player has more than two move point") {
      val investigator: Investigator[Possession] = new Investigator[Possession](1, 3, Nil)
      assert(move.isValid(investigator, new SetMap((1, 3) ::(2, 3) ::(1, 2) :: Nil)))
    }

  }


}
