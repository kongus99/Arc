package org.kon.game;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Kongus
 * Date: 27/12/12
 * Time: 18:55
 * To change this template use File | Settings | File Templates.
 */
public class BoardFieldTest {

    private BoardField f;

    @Before
    public void setup() {
        f = new BoardField(EFieldType.GREEN);
    }

    @Test
    public void fieldCanBeGreenLocation() {
        assertEquals(EFieldType.GREEN, f.type());
    }

    @Test
    public void fieldCanBeRedLocation() {
        f = new BoardField(EFieldType.RED);
        assertEquals(EFieldType.RED, f.type());
    }

    @Test
    public void fieldCanBeStreetLocation() {
        f = new BoardField(EFieldType.STREET);
        assertEquals(EFieldType.STREET, f.type());
    }

    @Test
    public void fieldInitiallyHoldZeroClues() {
        assertEquals(0, f.cluesNumber());
    }

    @Test
    public void fieldCanHaveCluesAdded() {
        f.addClue(5);
        assertEquals(5, f.cluesNumber());
    }

    @Test
    public void fieldCluesAddUp() {
        f.addClue(5);
        f.addClue(7);
        assertEquals(12, f.cluesNumber());
    }

    @Test
    public void fieldCluesCanBeRemoved() {
        f.addClue(7);
        f.removeClue(5);
        assertEquals(2, f.cluesNumber());
    }

    @Test
    public void allFieldCluesCanBeRemoved() {
        f.addClue(999);
        f.addClue(7);
        f.removeAllClues();
        assertEquals(0, f.cluesNumber());
    }

    @Test
    public void cluesCannotBeNegativeAndHaveToBeAtLeastZero() {
        f.addClue(9);
        f.removeClue(15);
        assertEquals(0, f.cluesNumber());
    }


}
