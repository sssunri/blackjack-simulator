
import java.util.Scanner;

public class Blackjack {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Java Casino!");

        int handValue = playGame();
        if (handValue == -1) {
            System.out.println("\nDealer wins!");
            return;
        }

        int dealerHand = dealerPlays();
        if (dealerHand == -1) {
            System.out.println("\nPlayer wins!");
            return;
        }

        determineWinner(handValue, dealerHand);
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

    public static void determineWinner(int handValue, int dealerHand) {
        if (handValue > dealerHand) {
            System.out.println("\nPlayer wins!");
        } else if (dealerHand > handValue) {
            System.out.println("\nDealer wins!");
        } else {
            System.out.println("\nIt's a tie!");
        }
    }

    // prompts the player to draw a card and validates input
    public static char askToDrawCard() {
        char response;

        do {
            System.out.print("\nDo you want to draw a card? (y/n): ");
            response = sc.nextLine().toLowerCase().charAt(0);
        } while (response != 'y' && response != 'n');

        return response;
    }

    public static int drawCard() {
        return (int) (Math.random() * 11 + 1);
    }
}
