package org.kon.game

import org.kon.game.Skill._
import org.scalatest.FlatSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.kon.game.SkillPair._

@RunWith(classOf[JUnitRunner])
class SkillSetOnePair extends FlatSpec {

  val skillSet: SkillSet = new SkillSet(Map(SPEED -> 1, SNEAK -> 3), 5)
  it should "be readable" in {
    assert(1 === skillSet.value(SPEED))
    assert(3 === skillSet.value(SNEAK))
  }
  it should "throw exception if some skill is missing" in {
    intercept[NoSuchElementException] {
      skillSet.value(FIGHT)
    }
  }
  it should "be adjustable 3 times to the right from starting position" in {
    val adjusted = skillSet.moveRight(SPEED_SNEAK).moveRight(SPEED_SNEAK).moveRight(SPEED_SNEAK)
    assert(4 === adjusted.value(SPEED))
    assert(0 === adjusted.value(SNEAK))
  }
  it should "not be adjustable more than 3 times to the right from the starting position" in {
    intercept[SkillAdjustmentOutOfRange] {
      skillSet.moveRight(SPEED_SNEAK).moveRight(SPEED_SNEAK).moveRight(SPEED_SNEAK).moveRight(SPEED_SNEAK)
    }
  }
  it should "not be adjustable to the left from the starting position" in {
    intercept[SkillAdjustmentOutOfRange] {
      skillSet.moveLeft(SPEED_SNEAK)
    }
  }
  it should "be adjustable right and left" in {
    val adjusted = skillSet.moveRight(SPEED_SNEAK).moveRight(SPEED_SNEAK).moveRight(SPEED_SNEAK).moveLeft(SPEED_SNEAK).moveLeft(SPEED_SNEAK)
    assert(2 === adjusted.value(SPEED))
    assert(2 === adjusted.value(SNEAK))
  }

  it should "have the same values after finishing all moves" in {
    val adjusted = skillSet.moveRight(SPEED_SNEAK).moveRight(SPEED_SNEAK).moveRight(SPEED_SNEAK).moveLeft(SPEED_SNEAK).moveLeft(SPEED_SNEAK).finish
    assert(2 === adjusted.value(SPEED))
    assert(2 === adjusted.value(SNEAK))
  }

  it should "should return to the base position after symmetrical moves" in {
    val adjusted = skillSet.moveRight(SPEED_SNEAK).moveLeft(SPEED_SNEAK)
    assert(1 === adjusted.value(SPEED))
    assert(3 === adjusted.value(SNEAK))
  }
}