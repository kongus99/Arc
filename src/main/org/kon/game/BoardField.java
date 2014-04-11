package org.kon.game;

/**
 * Created with IntelliJ IDEA.
 * User: Kongus
 * Date: 27/12/12
 * Time: 18:58
 * To change this template use File | Settings | File Templates.
 */
public class BoardField {
    private int clues;

    private final EFieldType type;

    public BoardField(EFieldType type) {
        this.type = type;
        clues = 0;
    }

    public EFieldType type() {
        return type;
    }

    public int cluesNumber() {
        return clues;
    }

    public void addClue(int clues) {
        this.clues += clues;
    }

    public void removeClue(int clues) {
        this.clues -= clues;
        if (this.clues < 0)
            this.clues = 0;
    }

    public void removeAllClues() {
        clues = 0;
    }
}
