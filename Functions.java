package app;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;

/**
 * Functions
 */
public class Functions {
    Map<PlayerCard, Card> hands = new ConcurrentHashMap<>();
    int playerCounter = 2;

    public void initDeal(int players, int dealer, int startCards) {

        for (int i = 1; i <= ((players + dealer) * 2); i++) {
            // System.out.println(i);

            // System.out.println((players + dealer) * 2);
            if (i == (players + dealer)) {
                hands.put(new PlayerCard("Dealer", 1), App.DeckInstance.dealCard());
                // System.out.println("Dealer 1");
            } else if (i == ((players + dealer) * 2)) {
                hands.put(new PlayerCard("Dealer", 2), App.DeckInstance.dealCard());
                // System.out.println("Dealer 2");
            } else if (i <= players) {
                hands.put(new PlayerCard("Player" + i, 1), App.DeckInstance.dealCard());
                // System.out.println("Player 1");
            } else if (i > (players + dealer) && i <= ((players + dealer) * 2)) {
                hands.put(new PlayerCard("Player" + (i - (players + dealer)), 2), App.DeckInstance.dealCard());
                // System.out.println("Player 2");

            }

            // System.out.println(hands);
            // return hands;
        }

    }// method

    public ArrayList<Card> getHands(String player) {
        ArrayList<Card> playerHand = new ArrayList<Card>(5);

        hands.forEach((k, v) -> {

            String getPlayer = k.getPlayer();
            // System.out.println(getPlayer);
            if (player.equalsIgnoreCase(getPlayer)) {
                // System.out.println("if " + k.getPlayer());
                playerHand.add(v);

            }

        });

        return playerHand;

    }

    public int cardEvaluator(Card card) {
        int numValue = 0;
        switch (card.toString().substring((card.toString().lastIndexOf('-') + 1), card.toString().length())) {
        case "ACE":
            numValue = 1;
            break;
        case "TWO":
            numValue = 2;
            break;
        case "THREE":
            numValue = 3;
            break;
        case "FOUR":
            numValue = 4;
            break;
        case "FIVE":
            numValue = 5;
            break;
        case "SIX":
            numValue = 6;
            break;
        case "SEVEN":
            numValue = 7;
            break;
        case "EIGHT":
            numValue = 8;
            break;
        case "NINE":
            numValue = 9;
            break;
        case "TEN":
            numValue = 10;
            break;
        case "JACK":
            numValue = 10;
            break;
        case "QUEEN":
            numValue = 10;
            break;
        case "KING":
            numValue = 10;
            break;
        default:
            break;
        }
        return numValue;
    }

    public int cardValueCalc(ArrayList<Card> cards) {
        int cardsValue = 0;

        for (Card card : cards) {

            cardsValue = cardsValue + cardEvaluator(card);

        }
        return cardsValue;
    }

    public void hit(String player) {
        try {
            hands.put(new PlayerCard(player, playerCounter), App.DeckInstance.dealCard());
        } catch (Exception e) {
            System.out.println("Exception <" + e.getClass().getSimpleName() + "> " + e.getMessage());
            playerCounter++;
            hit(player);
        } finally {
            playerCounter = 3;
        }

    }

    public void dealerAction() {
        while (App.FunctionsInstance.cardValueCalc(App.FunctionsInstance.getHands("Dealer")) < 16) {
            hit("Dealer");
        }
    }

    public String compareCards(String player) {
        int handPlayer = 0;
        int handDealer = 0;

        if (aceChecker(player)) {
            if (((App.FunctionsInstance.cardValueCalc(App.FunctionsInstance.getHands(player)) + 10) <= 21)) {
                handPlayer = App.FunctionsInstance.cardValueCalc(App.FunctionsInstance.getHands(player)) + 10;
            } else {
                handPlayer = App.FunctionsInstance.cardValueCalc(App.FunctionsInstance.getHands(player));
            }
        } else {
            handPlayer = App.FunctionsInstance.cardValueCalc(App.FunctionsInstance.getHands(player));
        }

        if (aceChecker("Dealer")) {
            if (((App.FunctionsInstance.cardValueCalc(App.FunctionsInstance.getHands("Dealer")) + 10) <= 21)) {
                handDealer = App.FunctionsInstance.cardValueCalc(App.FunctionsInstance.getHands("Dealer")) + 10;
            } else {
                handDealer= App.FunctionsInstance.cardValueCalc(App.FunctionsInstance.getHands("Dealer"));
            }
        } else {
            handDealer = App.FunctionsInstance.cardValueCalc(App.FunctionsInstance.getHands("Dealer"));
        }

        System.out.println(handDealer);
        System.out.println(handPlayer);
        if (handDealer >= handPlayer && handDealer<21) {
            return "Dealer";
        } else {
            return player;
        }
    }

    public boolean aceChecker(String player) {
        int i = 0;
        boolean tf = false;

        for (Card card : getHands(player)) {

            if (card.getValue().toString().equalsIgnoreCase("ACE")) {
                tf = true;
                break;
            } else if (i++ == getHands(player).size() - 1) {
                tf = false;
                break;
            } else {
                continue;
            } 
            
        }
        return tf;
    }

    public void clearHands() {

        hands.forEach((k, v) -> {
            hands.remove(k);
        });
    }// method
}// class