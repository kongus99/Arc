package org.kon.game

import org.kon.game.Skill.Skill


object SkillPair extends Enumeration {
  type SkillPair = Value
  val SPEED_SNEAK, FIGHT_WILL, LORE_LUCK = Value

  def thatContains(skill: Skill): SkillPair =
    if (skill == Skill.SPEED || skill == Skill.SNEAK) SPEED_SNEAK
    else if (skill == Skill.FIGHT || skill == Skill.WILL) FIGHT_WILL
    else LORE_LUCK
}

