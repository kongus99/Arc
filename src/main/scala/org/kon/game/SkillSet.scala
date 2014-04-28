package org.kon.game

import org.kon.game.Skill.Skill
import org.kon.game.SkillPair._

class SkillAdjustmentOutOfRange extends RuntimeException

class Inclination(value: Int) {
  val MIN = 0
  val MAX = 3

  def inclineLeft(position: Int): Inclination =
    if (value(position) > MIN) new Inclination(value - 1)
    else throw new SkillAdjustmentOutOfRange

  def inclineRight(position: Int): Inclination =
    if (value(position) < MAX) new Inclination(value + 1)
    else throw new SkillAdjustmentOutOfRange

  def value(position: Int): Int = position + value
}


class SkillSet(baseValues: Map[Skill, Int], focus: Int, positions: Map[SkillPair, Int], inclinations: Map[SkillPair, Inclination]) {
  def this(baseValues: Map[Skill, Int], focus: Int) =
    this(baseValues,
      focus,
      Map(SPEED_SNEAK -> 0, FIGHT_WILL -> 0, LORE_LUCK -> 0),
      Map(SPEED_SNEAK -> new Inclination(0), FIGHT_WILL -> new Inclination(0), LORE_LUCK -> new Inclination(0)))

  def value(s: Skill): Int = {
    val pair: SkillPair = SkillPair.thatContains(s)
    Skill.value(s, baseValues(s), position(pair))
  }

  def moveLeft(s: SkillPair): SkillSet = {
    val inc = inclinations(s).inclineLeft(positions(s))
    create(positions, inclinations.updated(s, inc))
  }

  def moveRight(s: SkillPair): SkillSet = {
    val inc = inclinations(s).inclineRight(positions(s))
    create(positions, inclinations.updated(s, inc))
  }

  def finish: SkillSet = {
    val inc = Map(SPEED_SNEAK -> new Inclination(0), FIGHT_WILL -> new Inclination(0), LORE_LUCK -> new Inclination(0))
    val pos = positions.updated(SPEED_SNEAK, position(SPEED_SNEAK))
      .updated(FIGHT_WILL, position(FIGHT_WILL))
      .updated(LORE_LUCK, position(LORE_LUCK))
    create(pos, inc)
  }

  def position(s: SkillPair): Int = {
    inclinations(s).value(positions(s))
  }

  def create(pos: Map[SkillPair, Int], incl: Map[SkillPair, Inclination]): SkillSet = new SkillSet(baseValues, focus, pos, incl)

}
