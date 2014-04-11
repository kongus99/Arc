abstract sealed class Slider(first: Skill, second: Skill) {
  def create(first: Skill, second: Skill): Slider

  def moveRight: Slider = create(first.moveRight, second.moveRight)

  def moveLeft: Slider = create(first.moveLeft, second.moveLeft)

  def value(s: Skill): Int = s match {
    case first.getClass => first.value
    case second.getClass => second.value
  }
}

sealed case class SPEED_SNEAK(baseSpeed: Int, baseSneak: Int, adjustment: Int) extends Slider(SPEED(baseSpeed, adjustment), SNEAK(baseSneak, adjustment)) {
  override def create(first: Skill, second: Skill): Slider = new SPEED_SNEAK(first.base, second.base, first.adjustment)
}

