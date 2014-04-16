package org.kon.game

import org.kon.game.Skill._
import org.scalatest.FunSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class SkillSliderSpec extends FunSpec {

  describe("A Speed Sneak Slider") {
    val slider: SpeedSneakSlider = new SpeedSneakSlider(1, 3, 0)
    it("can be checked") {
      assert(1 === slider.value(SPEED))
      assert(3 === slider.value(SNEAK))
    }
    it("does not contain Fight skill") {
      intercept[SkillNotOnThisSlider] {
        slider.value(FIGHT)
      }
    }
    it("can be adjusted 3 times to the right from starting position") {
      val adjusted = slider.moveRight.moveRight.moveRight
      assert(4 === adjusted.value(SPEED))
      assert(0 === adjusted.value(SNEAK))
    }
    it("cannot be adjusted more than 3 times to the right from the starting position") {
      intercept[SkillAdjustmentOutOfRange] {
        slider.moveRight.moveRight.moveRight.moveRight
      }
    }
    it("cannot be adjusted to the left from the starting position") {
      intercept[SkillAdjustmentOutOfRange] {
        slider.moveLeft
      }
    }
    it("can be adjusted right and left") {
      val adjusted = slider.moveRight.moveRight.moveRight.moveLeft.moveLeft
      assert(2 === adjusted.value(SPEED))
      assert(2 === adjusted.value(SNEAK))
    }
    it("can be checked for the skills it contains") {
      assert(true === slider.contains(SPEED))
      assert(true === slider.contains(SNEAK))
      assert(false === slider.contains(FIGHT))
    }
  }
}