package org.kon.game

import org.junit.Test
import org.junit.Assert._
import org.kon.game.Skill._

class SkillTest {
  @Test
  def testOK() = {
    assertEquals(1, Skill.value(WILL, 1, 0))
  }

  @Test
  def testNOK() = assertFalse(false)
}
