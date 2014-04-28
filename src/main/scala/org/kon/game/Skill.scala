package org.kon.game

object Skill extends Enumeration {
  type Skill = Value
  val SPEED, SNEAK, FIGHT, WILL, LORE, LUCK = Value

  val positive = Set(SPEED, FIGHT, LORE)

  def value(skill: Skill, base: Int, adjustment: Int): Int =
    if (positive.contains(skill)) base + adjustment
    else base + (-1) * adjustment
}



