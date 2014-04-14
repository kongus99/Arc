import org.kon.game.Skill._
import org.kon.game.SkillValue

sealed class SkillAdjustmentOutOfRange extends Exception

sealed class SkillNotOnThisSlider extends Exception

abstract sealed class Slider {
  val MIN = 0
  val MAX = 3

  def first: SkillValue

  def second: SkillValue

  def adjustment: Int

  def create(first: SkillValue, second: SkillValue, adjustment: Int): Slider

  def moveRight: Slider =
    if (adjustment < MAX) create(first, second, adjustment + 1)
    else throw new SkillAdjustmentOutOfRange

  def moveLeft: Slider =
    if (adjustment > MIN) create(first, second, adjustment - 1)
    else throw new SkillAdjustmentOutOfRange

  def value(s: Skill): Int =
    if (first.is(s)) first.currentValue(adjustment)
    else if (second.is(s)) second.currentValue(adjustment)
    else throw new SkillNotOnThisSlider

  def contains(s: Skill): Boolean = first.is(s) || second.is(s)
}

sealed case class SpeedSneakSlider(firstBase: Int, secondBse: Int, adjustment: Int) extends Slider {
  override def first: SkillValue = new SkillValue(SPEED, firstBase)

  override def second: SkillValue = new SkillValue(SNEAK, secondBse)

  override def create(first: SkillValue, second: SkillValue, adjustment: Int): Slider = new SpeedSneakSlider(first.baseValue, second.baseValue, adjustment)
}
