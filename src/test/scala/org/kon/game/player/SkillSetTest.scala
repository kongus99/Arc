package org.kon.game.player

import org.junit.runner.RunWith
import org.kon.game.player.Skill._
import org.kon.game.player.SkillPair._
import org.scalatest.FunSpec
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class SkillSetTest extends FunSpec {

  describe("A skill set with 5 focus") {

    val skillSet: SkillSet = new SkillSet(Map(SPEED -> 1, SNEAK -> 3, FIGHT -> 3, WILL -> 5, LORE -> 4, LUCK -> 6), 5)

    it("should be readable") {
      assertValuesCorrect(skillSet, 1, 3, 3, 5, 4, 6)
    }
    it("should be adjustable up to focus value to the right from starting position") {
      val adjusted = skillSet.moveRight(SPEED_SNEAK, 3).moveRight(FIGHT_WILL, 2)
      assertValuesCorrect(adjusted, 4, 0, 5, 3, 4, 6)
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

    it("should be adjustable more than focus value to the right from the starting position, provided we move back a little") {
      val adjusted = skillSet.moveRight(SPEED_SNEAK, 3).moveRight(FIGHT_WILL, 2).moveLeft(SPEED_SNEAK, 2).moveRight(LORE_LUCK, 2)
      assertValuesCorrect(adjusted, 2, 2, 5, 3, 6, 4)
    }

    it("should be adjustable right and left") {
      val adjusted = skillSet.moveRight(SPEED_SNEAK, 3).moveLeft(SPEED_SNEAK, 2)
      assertValuesCorrect(adjusted, 2, 2, 3, 5, 4, 6)
    }

    it("should have the same values after finishing all moves") {
      val adjusted = skillSet.moveRight(SPEED_SNEAK, 3).moveLeft(SPEED_SNEAK, 2).finish
      assertValuesCorrect(adjusted, 2, 2, 3, 5, 4, 6)
    }

    it("should be possible to move up to focus after finishing move") {
      val adjusted = skillSet.moveRight(SPEED_SNEAK, 3).moveRight(FIGHT_WILL, 2).finish.moveRight(FIGHT_WILL, 1).moveRight(LORE_LUCK, 3)
      assertValuesCorrect(adjusted, 4, 0, 6, 2, 7, 3)
    }

    it("should should return to the base position after symmetrical moves") {
      val adjusted = skillSet.moveRight(SPEED_SNEAK).moveLeft(SPEED_SNEAK)
      assertValuesCorrect(adjusted, 1, 3, 3, 5, 4, 6)
    }
  }


  def assertValuesCorrect(set: SkillSet, speed: Int, sneak: Int, fight: Int, will: Int, lore: Int, luck: Int) {
    assert(speed === set.value(SPEED))
    assert(sneak === set.value(SNEAK))
    assert(fight === set.value(FIGHT))
    assert(will === set.value(WILL))
    assert(lore === set.value(LORE))
    assert(luck === set.value(LUCK))
  }
}