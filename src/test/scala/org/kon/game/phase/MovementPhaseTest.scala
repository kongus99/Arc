package org.kon.game.phase

//
//import org.junit.runner.RunWith
//import org.kon.game.player.{Possession, Investigator}
//import org.scalatest.FunSpec
//import org.scalatest.junit.JUnitRunner
//
//@RunWith(classOf[JUnitRunner])
//class MovementPhaseTest extends FunSpec {
//  val investigator: Investigator[Possession] = new Investigator[Possession](1, 2, Nil)
//  val board = BoardFactory.create((1, 3) ::(3, 1) :: Nil, Nil, Nil)
//  val phase: MovementPhase = new MovementPhase(board)
//
//  val s: MovementStream = ???
//
//  describe("In a movement phase") {
//
//    it("an investigator can move once") {
//      val movedInvestigator: Investigator[Possession] = phase.move(investigator, s)
//      assert(movedInvestigator.currentPosition === 3)
//    }
//  }
//}
