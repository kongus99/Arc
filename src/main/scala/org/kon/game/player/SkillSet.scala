package org.kon.game.player

import org.kon.game.player.Skill.Skill
import org.kon.game.player.SkillPair._

class SkillAdjustmentOutOfRange extends RuntimeException

class OutOfFocus extends RuntimeException

class MissingSkillInSet extends RuntimeException

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
  require(baseValues.size == Skill.values.size, "All skills must be initialized with values")

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
    updateSet(s, inc)
  }

  def moveLeft(s: SkillPair, m: Int): SkillSet = {
    if (m > 0) moveLeft(s).moveLeft(s, m - 1)
    else this
  }

  def moveRight(s: SkillPair, m: Int): SkillSet = {
    if (m > 0) moveRight(s).moveRight(s, m - 1)
    else this
  }


  def updateSet(s: SkillPair, inc: Inclination): SkillSet = {
    val newSet = create(positions, inclinations.updated(s, inc))
    if (newSet.focusUsed > focus)
      throw new OutOfFocus
    newSet
  }

  def moveRight(s: SkillPair): SkillSet = {
    val inc = inclinations(s).inclineRight(positions(s))
    updateSet(s, inc)
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

  def focusUsed: Int = sum(inclinations(FIGHT_WILL) :: inclinations(SPEED_SNEAK) :: inclinations(LORE_LUCK) :: Nil)

  def sum(inc: List[Inclination]): Int =
    inc match {
      case Nil => 0
      case x :: xs => x.value(0) + sum(xs)
    }

  def create(pos: Map[SkillPair, Int], incl: Map[SkillPair, Inclination]): SkillSet = new SkillSet(baseValues, focus, pos, incl)

}
