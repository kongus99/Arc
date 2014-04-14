package org.kon.game

import org.kon.game.Skill.Skill

object Skill extends Enumeration {
  type Skill = Value
  val SPEED, SNEAK, FIGHT, WILL, LORE, LUCK = Value

  val positive = Set(SPEED, FIGHT, LORE)

  def value(skill: Skill, base: Int, adjustment: Int): Int =
    if (positive.contains(skill)) base + adjustment
    else base + (-1) * adjustment
}

class SkillValue(skill: Skill, base: Int) {
  def currentValue(adjustment: Int): Int = Skill.value(skill, base, adjustment)

  def is(s: Skill): Boolean = skill == s

  def baseValue: Int = base
}


