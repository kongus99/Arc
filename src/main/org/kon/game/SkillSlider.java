package org.kon.game;

/**
 * Created with IntelliJ IDEA.
 * User: ldudek
 * Date: 1/18/13
 * Time: 8:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class SkillSlider {
    public static final int MAX_ADJUSTMENT = 3;
    public static final int MIN_ADJUSTMENT = 0;
    private final Skill firstSkill;
    private final Skill secondSkill;
    private int skillAdjustment;

    public SkillSlider(Skill firstSkill, Skill secondSkill) {
        this.firstSkill = firstSkill;
        this.secondSkill = secondSkill;
        skillAdjustment = MIN_ADJUSTMENT;
    }

    public void adjustRight(int adjustment) {
        int newValue = skillAdjustment + adjustment;
        if (newValue > MAX_ADJUSTMENT)
            throw new SkillAdjustmentOutOfRange();
        skillAdjustment = newValue;
    }

    public void adjustLeft(int adjustment) {
        int newValue = skillAdjustment - adjustment;
        if (newValue < MIN_ADJUSTMENT)
            throw new SkillAdjustmentOutOfRange();
        skillAdjustment = newValue;
    }

    public int skillValue(SkillType type) {
        if (firstSkill.type == type)
            return firstSkill.baseValue - skillAdjustment;
        if (secondSkill.type == type)
            return secondSkill.baseValue + skillAdjustment;
        throw new SkillNotOnThisSlider();
    }

    public boolean contains(SkillType type) {
        if (firstSkill.type == type || secondSkill.type == type)
            return true;
        return false;
    }

    public static class SkillNotOnThisSlider extends RuntimeException {
    }

    public static class SkillAdjustmentOutOfRange extends RuntimeException {
    }
}
