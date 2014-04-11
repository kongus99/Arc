package org.kon.game;

public class Skill {
    public final SkillType type;

    public final int baseValue;

    public Skill(SkillType type, int baseValue) {
        this.type = type;
        this.baseValue = baseValue;
    }
}
