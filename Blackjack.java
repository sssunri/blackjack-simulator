
import java.util.Scanner;

public class Blackjack {

    public static Scanner sc = new Scanner(System.in);

    public static boolean exitGame = false;

    public static void main(String[] args) {
        System.out.println("Welcome to Java Casino!");

        do {
            // if the player has busted, in which case the dealer wins by default
            int handValue = playGame();
            if (handValue == -1) {
                System.out.println("\nDealer wins!");
            } else {
                // if the dealer has busted, in which case the player wins by default
                int dealerHand = dealerPlays();
                if (dealerHand == -1) {
                    System.out.println("\nPlayer wins!");
                } else {
                    determineWinner(handValue, dealerHand);
                }
            }

            exitGame = !playAnotherRound();
        } while (!exitGame);

        System.out.println("Thank you for playing! Goodbye.");
    }

    // manages the main game loop, allowing the player to draw cards and track hand value
    public static int playGame() {
        int handValue = 0;

        char response = askToDrawCard();

        while (response == 'y') {
            int card = drawCard();
            handValue += card;

            if (handValue > 21) {
                System.out.println("Your total is " + handValue + ". You've busted!");
                return -1;
            } else {
                System.out.println("Your current total is: " + handValue);
            }

            response = askToDrawCard();
        }

        return handValue;
    }

    // handles the dealer's turn to draw cards and track their hand value
    public static int dealerPlays() {
        System.out.println("\nDealer's turn.\n");

        int dealerHand = 0;
        int card;

        while (dealerHand < 17) {
            card = drawCard();
            dealerHand += card;

            if (dealerHand > 21) {
                System.out.println("Dealer's total is " + dealerHand + ". He'd busted!");
                return -1;
            } else {
                System.out.println("Dealer's current total is: " + dealerHand);
            }
        }

        return dealerHand;
    }

    // determines the winner between the player and the dealer based on their hand values
    public static void determineWinner(int handValue, int dealerHand) {
        if (handValue > dealerHand) {
            System.out.println("\nPlayer wins!");
        } else if (dealerHand > handValue) {
            System.out.println("\nDealer wins!");
        } else {
            System.out.println("\nIt's a tie!");
        }
    }

    // prompts the player to play another round and validates the input
    public static boolean playAnotherRound() {
        char response = ' ';

        do {
            System.out.print("\nWould you like to play another round? (y/n): ");
            String input = sc.nextLine().trim().toLowerCase();

            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please enter 'y' or 'n'.");
                continue;
            }

            response = input.charAt(0);

            if (response != 'y' && response != 'n') {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
            }
        } while (response != 'y' && response != 'n');

        return response == 'y';
    }

    // prompts the player to draw a card and validates input
    public static char askToDrawCard() {
        char response = ' ';

        do {
            System.out.print("\nDo you want to draw a card? (y/n): ");
            String input = sc.nextLine().trim().toLowerCase();

            if (input.isEmpty()) {
                System.out.print("Input cannot be empty. Please enter 'y' or 'n'.\n");
                continue;
            }

            response = input.charAt(0);

            if (response != 'y' && response != 'n') {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
            }
        } while (response != 'y' && response != 'n');

        return response;
    }

    public static int drawCard() {
        return (int) (Math.random() * 11 + 1);
    }
}
