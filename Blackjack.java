
import java.util.Scanner;

public class Blackjack {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Java Casino!");

        playGame();
    }

    // manages the main game loop, allowing the player to draw cards and track hand value
    public static void playGame() {
        int handValue = 0;

        char response = askToDrawCard();

        while (response == 'y') {
            int card = drawCard();
            handValue += card;

            if (handValue > 21) {
                System.out.println("Your total is " + handValue + ". You've busted!");
                break;
            } else {
                System.out.println("Your current total is: " + handValue);
            }

            response = askToDrawCard();
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
