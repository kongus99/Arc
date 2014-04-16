package org.kon.game

import org.kon.game.Skill._

class SkillAdjustmentOutOfRange extends RuntimeException

class SkillNotOnThisSlider extends RuntimeException

abstract sealed class SkillSlider {
  val MIN = 0
  val MAX = 3

  def first: SkillValue

  def second: SkillValue

  def adjustment: Int

  def create(first: SkillValue, second: SkillValue, adjustment: Int): SkillSlider

  def moveRight: SkillSlider =
    if (adjustment < MAX) create(first, second, adjustment + 1)
    else throw new SkillAdjustmentOutOfRange

  def moveLeft: SkillSlider =
    if (adjustment > MIN) create(first, second, adjustment - 1)
    else throw new SkillAdjustmentOutOfRange

  def value(s: Skill): Int =
    if (first.is(s)) first.currentValue(adjustment)
    else if (second.is(s)) second.currentValue(adjustment)
    else throw new SkillNotOnThisSlider

  def contains(s: Skill): Boolean = first.is(s) || second.is(s)
}

sealed case class SpeedSneakSlider(firstBase: Int, secondBase: Int, adjustment: Int) extends SkillSlider {
  override def first: SkillValue = new SkillValue(SPEED, firstBase)

  override def second: SkillValue = new SkillValue(SNEAK, secondBase)

  override def create(first: SkillValue, second: SkillValue, adjustment: Int): SkillSlider = new SpeedSneakSlider(first.baseValue, second.baseValue, adjustment)
}

sealed case class FightWillSlider(firstBase: Int, secondBase: Int, adjustment: Int) extends SkillSlider {
  override def first: SkillValue = new SkillValue(FIGHT, firstBase)

  override def second: SkillValue = new SkillValue(WILL, secondBase)

  override def create(first: SkillValue, second: SkillValue, adjustment: Int): SkillSlider = new FightWillSlider(first.baseValue, second.baseValue, adjustment)
}

sealed case class LoreLuckSlider(firstBase: Int, secondBase: Int, adjustment: Int) extends SkillSlider {
  override def first: SkillValue = new SkillValue(LORE, firstBase)

  override def second: SkillValue = new SkillValue(LUCK, secondBase)

  override def create(first: SkillValue, second: SkillValue, adjustment: Int): SkillSlider = new LoreLuckSlider(first.baseValue, second.baseValue, adjustment)
}
