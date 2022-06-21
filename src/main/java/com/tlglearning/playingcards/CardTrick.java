package com.tlglearning.playingcards;

import com.tlglearning.playingcards.model.Card;
import com.tlglearning.playingcards.model.Deck;
import com.tlglearning.playingcards.model.Suit;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

public class CardTrick {

    private Deque<Card> blackPile;
    private Deque<Card> redPile;

    public CardTrick() {
        blackPile = new LinkedList<>();
        redPile = new LinkedList<>();
    }

    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();

        CardTrick trick = new CardTrick();
        trick.spiltDeck(deck);

        // TODO (Optional: Shuffle the red deque and black deque)

        trick.swapCards();

        // TODO Sort each deque by color, suit, and rank,

        trick.tally();

    }

    public void spiltDeck(Deck deck) {
        while (deck.getRemaining() > 0) {
            Card indicator = deck.draw();
            Card next = deck.draw();
            if (indicator.getSuit().getColor() == Suit.Color.BLACK) {
                blackPile.add(next);
            } else {
                redPile.add(next);
            }
        }
    }

    public void swapCards() {
        Random rng = new Random();
        int swapSize = rng.nextInt(1 + Math.min(blackPile.size(), redPile.size()));
        for (int i = 0; i < swapSize; i++) {
            redPile.add(blackPile.remove());
            blackPile.add(redPile.remove());
        }
    }

    public void tally() {
        int redCount = 0;
        int blackCount = 0;
        for (Card card : blackPile) {
            if (card.getSuit().getColor() == Suit.Color.BLACK) {
                blackCount++;
            }
        }
        for (Card card : redPile) {
            if (card.getSuit().getColor() == Suit.Color.RED) {
                redCount++;
            }
        }
        System.out.printf("Black: count=%d, cards=%s%n", blackCount, blackPile);
        System.out.printf("Red: count=%d, cards=%s%n", redCount, redPile);
    }

}
