package org.kon.game;

import org.kon.game.interfaces.IGameDatabase;
import org.kon.game.interfaces.IGameGraphics;

/**
 * Created with IntelliJ IDEA.
 * User: Kongus
 * Date: 27/12/12
 * Time: 18:32
 * To change this template use File | Settings | File Templates.
 */
public class Board {
    private final IGameGraphics graphics;
    private final IGameDatabase base;
    private int terrorLevel;

    public Board(IGameGraphics graphics, IGameDatabase base) {
        this.graphics = graphics;
        this.base = base;
    }

    public void setup() {
        base.getGates().shuffle();
        for (BoardField field : base.getBoardFields())
            addClueToUnstableField(field);
        graphics.drawBoard();
        terrorLevel = 0;
    }

    private void addClueToUnstableField(BoardField field) {
        if (field.type().equals(EFieldType.RED))
            field.addClue(1);
    }

    public int terrorLevel() {
        return terrorLevel;
    }

    public void increaseTerrorLevel(int level) {
        terrorLevel += level;
    }

    public void decreaseTerrorLevel(int level) {
        terrorLevel -= level;
        if (terrorLevel < 0)
            terrorLevel = 0;
    }
}
