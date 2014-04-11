package org.kon.game;

/**
 * Created with IntelliJ IDEA.
 * User: ldudek
 * Date: 1/18/13
 * Time: 8:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class Skill {
    public final SkillType type;

    public final int baseValue;

    public Skill(SkillType type, int baseValue) {
        this.type = type;
        this.baseValue = baseValue;
    }
}
