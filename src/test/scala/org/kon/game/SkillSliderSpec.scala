package org.kon.game

import org.kon.game.Skill._
import org.scalatest.FlatSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

trait SkillSliderSpec {
  this: FlatSpec =>

  def correctSlider(slider: => SkillSlider, firstSkill: Skill, secondSkill: Skill, skillNotPresent: Skill) {

    it should "be readable" in {
      assert(1 === slider.value(firstSkill))
      assert(3 === slider.value(secondSkill))
    }
    it should "not contain all skills" in {
      intercept[SkillNotOnThisSlider] {
        slider.value(skillNotPresent)
      }
    }
    it should "be adjustable 3 times to the right from starting position" in {
      val adjusted = slider.moveRight.moveRight.moveRight
      assert(4 === adjusted.value(firstSkill))
      assert(0 === adjusted.value(secondSkill))
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
      assert(2 === adjusted.value(firstSkill))
      assert(2 === adjusted.value(secondSkill))
    }
    it should "allow checking for the skills it contains" in {
      assert(true === slider.contains(firstSkill))
      assert(true === slider.contains(secondSkill))
      assert(false === slider.contains(skillNotPresent))
    }
  }
}

@RunWith(classOf[JUnitRunner])
class AllSlidersSpec extends FlatSpec with SkillSliderSpec {
  val speedSneak: SpeedSneakSlider = new SpeedSneakSlider(1, 3, 0)
  val fightWill: FightWillSlider = new FightWillSlider(1, 3, 0)
  val loreLuck: LoreLuckSlider = new LoreLuckSlider(1, 3, 0)
  "A Speed and Sneak slider" should behave like correctSlider(speedSneak, SPEED, SNEAK, FIGHT)
  "A Fight and Will slider" should behave like correctSlider(fightWill, FIGHT, WILL, SPEED)
  "A Lore and Luck slider" should behave like correctSlider(loreLuck, LORE, LUCK, WILL)
}