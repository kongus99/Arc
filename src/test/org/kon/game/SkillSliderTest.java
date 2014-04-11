package org.kon.game;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: ldudek
 * Date: 1/18/13
 * Time: 8:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class SkillSliderTest {


    private Skill s1;
    private Skill s2;
    private SkillSlider slider;

    @Before
    public void setUp() throws Exception {
        s1 = new Skill(SkillType.SPEED, 3);
        s2 = new Skill(SkillType.SNEAK, 1);
        slider = new SkillSlider(s1, s2);

    }

    @Test
    public void skillsCanBeChecked() throws Exception {
        assertEquals(3, slider.skillValue(SkillType.SPEED));
        assertEquals(1, slider.skillValue(SkillType.SNEAK));
    }

    @Test(expected = SkillSlider.SkillNotOnThisSlider.class)
    public void skillChecksForWrongSkillNamesThrowException() throws Exception {
        slider.skillValue(SkillType.FIGHT);
    }


    @Test
    public void skillSlidersCanBeAdjustedRightUpToThreeTimes() throws Exception {
        slider.adjustRight(3);
        assertEquals(0, slider.skillValue(SkillType.SPEED));
        assertEquals(4, slider.skillValue(SkillType.SNEAK));
    }

    @Test(expected = SkillSlider.SkillAdjustmentOutOfRange.class)
    public void skillSlidersCannotBeAdjustedRightMoreThanThreeTimes() throws Exception {
        slider.adjustRight(4);
    }

    @Test(expected = SkillSlider.SkillAdjustmentOutOfRange.class)
    public void skillSlidersCannotBeAdjustedLeftBelowBaseValues() throws Exception {
        slider.adjustLeft(1);
    }

    @Test
    public void skillSlidersCanBeAdjustedRightAndLeft() throws Exception {
        slider.adjustRight(3);
        slider.adjustLeft(2);
        assertEquals(2, slider.skillValue(SkillType.SPEED));
        assertEquals(2, slider.skillValue(SkillType.SNEAK));
    }

    @Test
    public void slidersCanBeCheckedForSkillsTheyContain() throws Exception {
        assertTrue(slider.contains(SkillType.SPEED));
        assertTrue(slider.contains(SkillType.SNEAK));
        assertFalse(slider.contains(SkillType.FIGHT));

    }

}
