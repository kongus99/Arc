sealed class SkillAdjustmentOutOfRange extends Exception

sealed class SkillNotOnThisSlider extends Exception

abstract sealed class Slider {
  val MIN = 0
  val MAX = 3

  def first: Skill

  def second: Skill

  def adjustment: Int

  def create(first: Skill, second: Skill, adjustment: Int): Slider

  def moveRight: Slider = if (adjustment < MAX) create(first, second, adjustment + 1) else throw new SkillAdjustmentOutOfRange

  def moveLeft: Slider = if (adjustment > MIN) create(first, second, adjustment - 1) else throw new SkillAdjustmentOutOfRange

  def value(s: Skill): Int

  def contains(s: Skill): Boolean
}

sealed case class SPEED_SNEAK(firstBase: Int, secondBse: Int, adjustment: Int) extends Slider {
  override def first: Skill = SPEED(firstBase)

  override def second: Skill = SNEAK(secondBse)

  override def create(first: Skill, second: Skill, adjustment: Int): Slider = new SPEED_SNEAK(first.base, second.base, adjustment)

  override def value(s: Skill): Int = s match {
    case SPEED(_) => first.value(adjustment)
    case SNEAK(_) => second.value(adjustment)
    case _ => throw new SkillNotOnThisSlider
  }

  override def contains(s: Skill): Boolean = s match {
    case SPEED(_) => true
    case SNEAK(_) => true
    case _ => false
  }


}
