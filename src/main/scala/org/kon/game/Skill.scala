sealed class SkillAdjustmentOutOfRange extends Exception

abstract sealed class Skill {
  val MIN = 0
  val MAX = 3

  def base: Int

  def adjustment: Int

  def sign: Int

  def value = base + sign * adjustment

  def create(base: Int, adjustment: Int): Skill

  def moveRight: Skill = if (adjustment > MIN) create(base, adjustment - 1) else throw new SkillAdjustmentOutOfRange

  def moveLeft: Skill = if (adjustment < MAX) create(base, adjustment + 1) else throw new SkillAdjustmentOutOfRange
}

sealed case class SPEED(base: Integer, adjustment: Integer) extends Skill {
  override def create(base: Int, adjustment: Int): Skill = new SPEED(base, adjustment)

  override def sign: Int = 1
}

sealed case class SNEAK(base: Integer, adjustment: Integer) extends Skill {
  override def create(base: Int, adjustment: Int): Skill = new SNEAK(base, adjustment)

  override def sign: Int = -1
}

sealed case class FIGHT(base: Integer, adjustment: Integer) extends Skill {
  override def create(base: Int, adjustment: Int): Skill = new FIGHT(base, adjustment)

  override def sign: Int = 1
}

sealed case class WILL(base: Integer, adjustment: Integer) extends Skill {
  override def create(base: Int, adjustment: Int): Skill = new WILL(base, adjustment)

  override def sign: Int = -1
}

sealed case class LORE(base: Integer, adjustment: Integer) extends Skill {
  override def create(base: Int, adjustment: Int): Skill = new LORE(base, adjustment)

  override def sign: Int = 1
}

sealed case class LUCK(base: Integer, adjustment: Integer) extends Skill {
  override def create(base: Int, adjustment: Int): Skill = new LUCK(base, adjustment)

  override def sign: Int = -1
}
