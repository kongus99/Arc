package org.kon.game;

/**
 * Created with IntelliJ IDEA.
 * User: Kongus
 * Date: 18/01/13
 * Time: 19:06
 * To change this template use File | Settings | File Templates.
 */
public class Focus {
    private final int maxFocusValue;
    private final SkillSlider[] sliders;


    public Focus(int focusValue, SkillSlider... sliders) {
        this.sliders = sliders;
        maxFocusValue = focusValue;
    }

    public void adjustSkill(SkillType type, int adjustment) {
    }

    public SkillSlider findSlider(SkillType type) {
        for (SkillSlider slider : sliders)
            if (slider.contains(type))
                return slider;
        throw new SkillNotFoundException();
    }

    public int remaining() {
        return -1;
    }

    public static class SkillNotFoundException extends RuntimeException {
    }

}
