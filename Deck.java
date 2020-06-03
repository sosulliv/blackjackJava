package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
    ArrayList<Card> deck = new ArrayList<Card>(52);

    public Deck() {
    }

    public Deck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public ArrayList<Card> getDeck() {
        return this.deck;
    }

    public void createDeck() {
        for (Suit cardSuit : Suit.values()) {
            for (Value cardValue : Value.values()) {
                this.deck.add(new Card(cardSuit, cardValue));
            } // innerfor
        } // outerfor
    }// createdeck
    
    public void shuffle() {
        Collections.shuffle(deck, new Random());
    } // shuffle

    public Card dealCard() {
       Card card = deck.get(0);
       deck.remove(card);
    return card;
    } // dealCard

}