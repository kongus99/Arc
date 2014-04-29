package org.kon.game

import org.kon.game.Skill._
import org.scalatest.FunSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.kon.game.SkillPair._

@RunWith(classOf[JUnitRunner])
class SkillSetTest extends FunSpec {

  describe("A skill set with 5 focus") {

    val skillSet: SkillSet = new SkillSet(Map(SPEED -> 1, SNEAK -> 3, FIGHT -> 3, WILL -> 5, LORE -> 4, LUCK -> 6), 5)

    it("should be readable") {
      assert(1 === skillSet.value(SPEED))
      assert(3 === skillSet.value(SNEAK))
      assert(3 === skillSet.value(FIGHT))
      assert(5 === skillSet.value(WILL))
      assert(4 === skillSet.value(LORE))
      assert(6 === skillSet.value(LUCK))
    }
    it("should be adjustable up to focus value to the right from starting position") {
      val adjusted = skillSet.moveRight(SPEED_SNEAK, 3).moveRight(FIGHT_WILL, 2)
      assert(4 === adjusted.value(SPEED))
      assert(0 === adjusted.value(SNEAK))
      assert(5 === adjusted.value(FIGHT))
      assert(3 === adjusted.value(WILL))
      assert(4 === skillSet.value(LORE))
      assert(6 === skillSet.value(LUCK))
    }
    it("should not be adjustable more than 3 times to the right from the starting position") {
      intercept[SkillAdjustmentOutOfRange] {
        skillSet.moveRight(SPEED_SNEAK, 4)
      }
    }
    it("should not be adjustable to the left from the starting position") {
      intercept[SkillAdjustmentOutOfRange] {
        skillSet.moveLeft(SPEED_SNEAK)
      }
    }
    it("should not be adjustable more than focus value to the right from the starting position") {
      intercept[OutOfFocus] {
        skillSet.moveRight(SPEED_SNEAK, 3).moveRight(FIGHT_WILL, 3)
      }
    }
    it("should be adjustable right and left") {
      val adjusted = skillSet.moveRight(SPEED_SNEAK, 3).moveLeft(SPEED_SNEAK, 2)
      assert(2 === adjusted.value(SPEED))
      assert(2 === adjusted.value(SNEAK))
    }

    it("should have the same values after finishing all moves") {
      val adjusted = skillSet.moveRight(SPEED_SNEAK, 3).moveLeft(SPEED_SNEAK, 2).finish
      assert(2 === adjusted.value(SPEED))
      assert(2 === adjusted.value(SNEAK))
    }

    it("should should return to the base position after symmetrical moves") {
      val adjusted = skillSet.moveRight(SPEED_SNEAK).moveLeft(SPEED_SNEAK)
      assert(1 === adjusted.value(SPEED))
      assert(3 === adjusted.value(SNEAK))
    }
  }

}