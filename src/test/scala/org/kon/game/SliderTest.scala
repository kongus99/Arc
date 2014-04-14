package org.kon.game

import org.junit.Test
import org.junit.Assert._
import org.kon.game.Skill._


class SliderTest {
  val slider: SpeedSneakSlider = new SpeedSneakSlider(1, 3, 0)

  @Test
  def skillsCanBeChecked(): Unit = {
    assertEquals(1, slider.value(SPEED))
    assertEquals(3, slider.value(SNEAK))
  }

  @Test
  def skillChecksForWrongSkillNamesThrowException(): Unit = {
    try {
      slider.value(FIGHT)
      fail()
    } catch {
      case ex: SkillNotOnThisSlider =>
    }
  }


  @Test
  def skillSlidersCanBeAdjustedRightUpToThreeTimes(): Unit = {
    val adjusted = slider.moveRight.moveRight.moveRight
    assertEquals(4, adjusted.value(SPEED))
    assertEquals(0, adjusted.value(SNEAK))
  }

  @Test
  def skillSlidersCannotBeAdjustedRightMoreThanThreeTimes(): Unit = {
    try {
      slider.moveRight.moveRight.moveRight.moveRight
      fail()
    } catch {
      case ex: SkillAdjustmentOutOfRange =>
    }
  }

  @Test
  def skillSlidersCannotBeAdjustedLeftBelowBaseValues(): Unit = {
    try {
      slider.moveLeft
      fail()
    } catch {
      case ex: SkillAdjustmentOutOfRange =>
    }
  }

  @Test
  def skillSlidersCanBeAdjustedRightAndLeft(): Unit = {
    val adjusted = slider.moveRight.moveRight.moveRight.moveLeft.moveLeft
    assertEquals(2, adjusted.value(SPEED))
    assertEquals(2, adjusted.value(SNEAK))
  }

  @Test
  def slidersCanBeCheckedForSkillsTheyContain(): Unit = {
    assertTrue(slider.contains(SPEED))
    assertTrue(slider.contains(SNEAK))
    assertFalse(slider.contains(FIGHT))
  }
}