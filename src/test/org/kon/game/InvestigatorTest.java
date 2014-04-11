package org.kon.game;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: ldudek
 * Date: 1/8/13
 * Time: 12:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class InvestigatorTest {

    private List<Possession> possessions;
    private Possession p1;
    private Possession p2;
    private Possession p3;
    private Possession p4;
    private Random seed;
    private Deck<Possession> deck;

    @Before
    public void setUp() throws Exception {
        possessions = new ArrayList();
        p1 = new Possession();
        p2 = new Possession();
        p3 = new Possession();
        p4 = new Possession();
        possessions.add(p1);
        possessions.add(p2);
        possessions.add(p3);
        possessions.add(p4);
        seed = new Random();
        deck = new Deck(possessions, seed);
    }

    @Test
    public void investigatorShouldHaveListOfFixedPossessions() throws Exception {
        Investigator investigator = new Investigator(0, possessions.subList(2, 4), deck);

        checkInvestigatorPossesses(investigator, p3, p4);
        checkInvestigatorNotPossesses(investigator, p1, p2);
        checkCardWasRemovedFromDeck(p3);
        checkCardWasRemovedFromDeck(p4);
        checkCardIsStillInDeck(p1);
        checkCardIsStillInDeck(p2);
    }

    @Test
    public void investigatorShouldHaveRandomPossessions() throws Exception {
        Investigator investigator = new Investigator(2, possessions.subList(3, 4),deck);

        checkInvestigatorPossesses(investigator, p1, p2, p4);
        checkInvestigatorNotPossesses(investigator, p3);
        checkCardWasRemovedFromDeck(p1);
        checkCardWasRemovedFromDeck(p2);
        checkCardWasRemovedFromDeck(p4);
        checkCardIsStillInDeck(p3);
    }

    private void checkInvestigatorNotPossesses(Investigator investigator, Possession... possessions) {
        for (Possession possession : possessions)
            assertFalse(investigator.has(possession));
    }

    private void checkCardIsStillInDeck(Possession expected) {
        assertEquals(expected, deck.drawFirst());
    }

    private void checkCardWasRemovedFromDeck(Possession p) {
        try {
            deck.draw(p);
            fail();
        } catch (Deck.DeckElementNotFound e) {
        }
    }

    private void checkInvestigatorPossesses(Investigator investigator, Possession... possessions) {
        for (Possession possession : possessions)
            assertTrue(investigator.has(possession));
    }
}