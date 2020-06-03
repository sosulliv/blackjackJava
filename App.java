package app;

import java.util.Scanner;

public class App {
    public static Deck DeckInstance = new Deck();
    public static Functions FunctionsInstance = new Functions();
    public static int numCards = 2;
    public static int dealers = 1;
    public static int players = 1;
    

    public static void main(String[] args) throws Exception {

        App.DeckInstance.createDeck();
        App.DeckInstance.shuffle();
        App.FunctionsInstance.initDeal(players, dealers, numCards);

        Scanner scanner = new Scanner(System.in); // Create a Scanner object
        System.out.println("Welcome to BlackJack!!");
        System.out.println("");
        System.out.print("The Dealer Shows");
        System.out.println(App.FunctionsInstance.getHands("Dealer").get(0));
        System.out.print("The Player Shows");
        System.out.println(App.FunctionsInstance.getHands("Player1"));


        //System.out.println(App.FunctionsInstance.cardValueCalc((App.FunctionsInstance.getHands("Dealer"))) + 10 == 21);

        if ((App.FunctionsInstance.cardValueCalc(App.FunctionsInstance.getHands("Dealer")) + 10 == 21) && App.FunctionsInstance.aceChecker("Dealer")) {
            System.out.print(App.FunctionsInstance.getHands("Dealer"));
            System.out.print(App.FunctionsInstance.getHands("Player1"));
            System.out.println("Dealer has Blackjack.  Dealer wins.");

        } else if ((App.FunctionsInstance.cardValueCalc(App.FunctionsInstance.getHands("Player1")) + 10 == 21) && App.FunctionsInstance.aceChecker("Player1")) {
            System.out.print(App.FunctionsInstance.getHands("Dealer"));
            System.out.print(App.FunctionsInstance.getHands("Player1"));
            System.out.println("You have Blackjack.  You win.");

        } else {
            for (int i = 0; i < 5; i++) {
                System.out.println("Player1: Stay(s) or Hit(h)h");
                String playersInput = scanner.nextLine();
                if ((char) playersInput.charAt(0) == (char) "s".charAt(0)) {
                    App.FunctionsInstance.dealerAction();

                    if (App.FunctionsInstance.compareCards("Player1").equalsIgnoreCase("Player1")) {
                        System.out.println("Player1 Win!");
                        System.out.println(App.FunctionsInstance.getHands("Dealer"));
                        System.out.println(App.FunctionsInstance.getHands("Player1"));

                        break;
                    } else {
                        System.out.println("Dealer Win!");
                        System.out.println(App.FunctionsInstance.getHands("Dealer"));
                        System.out.println(App.FunctionsInstance.getHands("Player1"));
                        break;
                    }
                    // calc and compare scores.

                } else if ((char) playersInput.charAt(0) == (char) "h".charAt(0)) {
                    // hit
                    App.FunctionsInstance.hit("Player1");
                    System.out.print("The Player Shows");
                    System.out.println(App.FunctionsInstance.getHands("Player1"));
                    if (App.FunctionsInstance.cardValueCalc(App.FunctionsInstance.getHands("Player1")) > 21) {
                        System.out.print("You Lose ");
                        System.out.println(
                                App.FunctionsInstance.cardValueCalc(App.FunctionsInstance.getHands("Player1")));
                        break;
                    }
                    ;
                }

            }

        }

        App.FunctionsInstance.clearHands();
        App.DeckInstance.shuffle();


        scanner.close();

    }
}
