package org.kon.game

import org.kon.game.Skill._
import org.scalatest.FlatSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class SkillSliderSpec extends FlatSpec {

    val slider: SpeedSneakSlider = new SpeedSneakSlider(1, 3, 0)
  it should "be readable" in {
      assert(1 === slider.value(SPEED))
      assert(3 === slider.value(SNEAK))
    }
  it should "not contain all skills" in {
      intercept[SkillNotOnThisSlider] {
        slider.value(FIGHT)
      }
    }
  it should "be adjustable 3 times to the right from starting position" in {
      val adjusted = slider.moveRight.moveRight.moveRight
      assert(4 === adjusted.value(SPEED))
      assert(0 === adjusted.value(SNEAK))
    }
  it should "not be adjustable more than 3 times to the right from the starting position" in {
      intercept[SkillAdjustmentOutOfRange] {
        slider.moveRight.moveRight.moveRight.moveRight
      }
    }
  it should "not be adjustable to the left from the starting position" in {
      intercept[SkillAdjustmentOutOfRange] {
        slider.moveLeft
      }
    }
  it should "be adjustable right and left" in {
      val adjusted = slider.moveRight.moveRight.moveRight.moveLeft.moveLeft
      assert(2 === adjusted.value(SPEED))
      assert(2 === adjusted.value(SNEAK))
    }
  it should "allow checking for the skills it contains" in {
      assert(true === slider.contains(SPEED))
      assert(true === slider.contains(SNEAK))
      assert(false === slider.contains(FIGHT))
    }

}