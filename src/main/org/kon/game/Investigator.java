package org.kon.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ldudek
 * Date: 1/10/13
 * Time: 9:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class Investigator {
    private List<Possession> fixedPossessions;
    private List<Possession> currentPossessions;

    public Investigator(int randomPossessions, List<Possession> fixedPossessions, Deck<Possession> deck) {
        createInventory();
        assignFixedPossessions(fixedPossessions, deck);
        assignRandomPossessions(randomPossessions, deck);
    }

    public boolean has(Possession possession) {
        return currentPossessions.contains(possession);
    }

    private void assignRandomPossessions(int randomPossessionsNumber, Deck<Possession> deck) {
        for (int i = 0; i < randomPossessionsNumber; i++) {
            Possession p = deck.drawFirst();
            currentPossessions.add(p);
        }
    }

    private void createInventory() {
        currentPossessions = new ArrayList();
        fixedPossessions = new ArrayList();
    }

    private void assignFixedPossessions(List<Possession> possessions, Deck<Possession> deck) {
        for (Possession possession : possessions) {
            Possession p = deck.draw(possession);
            currentPossessions.add(p);
        }
    }
}
