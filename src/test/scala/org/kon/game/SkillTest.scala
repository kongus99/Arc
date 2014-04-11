package org.kon.game

import org.junit.Test
import org.junit.Assert._

class SkillTest {
  @Test
  def testOK() = {
    assertEquals(1, new SPEED(1).value(0))
  }

  @Test
  def testNOK() = assertFalse(false)
}
