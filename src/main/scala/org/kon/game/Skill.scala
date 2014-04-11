abstract sealed class Skill {
  def base: Int

  def sign: Int

  def value(adjustment: Int) = base + sign * adjustment
}

sealed case class SPEED(base: Int) extends Skill {
  override def sign: Int = 1
}

sealed case class SNEAK(base: Int) extends Skill {
  override def sign: Int = -1
}

sealed case class FIGHT(base: Int) extends Skill {
  override def sign: Int = 1
}

sealed case class WILL(base: Int) extends Skill {
  override def sign: Int = -1
}

sealed case class LORE(base: Int) extends Skill {
  override def sign: Int = 1
}

sealed case class LUCK(base: Int) extends Skill {
  override def sign: Int = -1
}
