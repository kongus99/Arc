package org.kon.game;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

/**
 * Created with IntelliJ IDEA.
 * User: Kongus
 * Date: 27/12/12
 * Time: 20:18
 * To change this template use File | Settings | File Templates.
 */
public class DeckTest {

    private Random seed;
    private List<Integer> integerList;
    private Deck deck;
    private Integer firstNumber;
    private Integer secondNumber;
    private Integer thirdNumber;

    @Before
    public void setUp() throws Exception {
        seed = mock(Random.class);
        integerList = new ArrayList<Integer>();
        firstNumber = 1;
        secondNumber = 2;
        thirdNumber = 3;
        doReturn(2).when(seed).nextInt(3);
        doReturn(1).when(seed).nextInt(2);
        doReturn(0).when(seed).nextInt(1);
        integerList.add(firstNumber);
        integerList.add(secondNumber);
        integerList.add(thirdNumber);
        deck = new Deck<Integer>(integerList, seed);
    }

    @Test
    public void shuffleShouldChangeTheSequence() throws Exception {
        deck.shuffle();
        assertEquals(thirdNumber, deck.drawFirst());
        assertEquals(secondNumber, deck.drawFirst());
        assertEquals(firstNumber, deck.drawFirst());
    }

    @Test
    public void drawingShouldReturnTheOriginalSequence() throws Exception {
        assertEquals(firstNumber, deck.drawFirst());
        assertEquals(secondNumber, deck.drawFirst());
        assertEquals(thirdNumber, deck.drawFirst());
    }

    @Test(expected = Deck.NotEnoughDeckElements.class)
    public void drawingTooManyShouldThrowAnException() throws Exception {
        int deckSize = integerList.size();
        for (int i = 0; i < deckSize + 1; i++)
            deck.drawFirst();
    }

    @Test(expected = Deck.DeckElementNotFound.class)
    public void drawShouldThrowExceptionWhenElementIsNotFound() throws Exception {
        deck = new Deck(new ArrayList(), seed);
        deck.draw(firstNumber);
    }

    @Test
    public void drawShouldReturnElementIfItIsFound() throws Exception {
        assertEquals(firstNumber, deck.draw(firstNumber));
    }


}
