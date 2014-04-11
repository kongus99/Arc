package org.kon.game;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: ldudek
 * Date: 1/3/13
 * Time: 9:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class Deck<E> {

    private final List<E> deck;
    private final Random randomSeed;

    public Deck(List<E> initialCards, Random seed) {
        deck = new LinkedList<E>(initialCards);
        randomSeed = seed;
    }

    public E drawFirst() {
        if (deck.size() > 0)
            return deck.remove(0);
        throw new NotEnoughDeckElements();
    }

    public void shuffle() {
        for (int index = deck.size(); index > 0; index--)
            moveSingleElement(index);
    }

    public E draw(E element) {
        int indexOfElement = deck.indexOf(element);
        if (indexOfElement >= 0)
            return deck.remove(indexOfElement);
        throw new DeckElementNotFound();
    }

    private void moveSingleElement(int index) {
        int moveIndex = randomSeed.nextInt(index);
        deck.add(deck.remove(moveIndex));
    }

    public static class NotEnoughDeckElements extends RuntimeException {
    }

    public static class DeckElementNotFound extends RuntimeException {
    }
}
