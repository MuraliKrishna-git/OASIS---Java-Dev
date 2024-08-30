import java.util.*;

public class p1_Guess_the_number {
    public static int total = 100;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        int Score = 0, width = 100, tot = 0;
        boolean d = true;
        box();
        center("GUESS THE NUMBER\n\n");
        box();
        center("Welcome to the Game!!!\n\n");
        box();
        while(d) {
            center("Play the game?? (Y/N) : ", 1);
            char da = scanner.next().charAt(0);
            if(da=='N'){ d = false; System.out.println(); center("THANK YOU!!"); continue; }
            System.out.println();
            center("Enter the number of Rounds you want to play: ", 1);
            int Rounds = scanner.nextInt();
            System.out.println();
            center("Enter the max life that you needed to play: ", 1);
            int Attempts = scanner.nextInt();
            System.out.println();
            System.out.println();
            for (int round = 1; round <= Rounds; round++) {
                int targetNumber = rand.nextInt(100) + 1;
                int repeat = 0;
                int score = Attempts * 10;
                tot = score;
                boolean crt = false;
                center("Round " + round + ":");
                System.out.println();
                center("I have generated a number between 1 and 100. Can you guess it?");
                while (repeat < Attempts && !crt) {
                    center("Enter your guess: ", 1);
                    int userGuess = scanner.nextInt();
                    repeat++;
                    if (userGuess == targetNumber) {
                        center("Congratulations! You've guessed the number in " + repeat + " attempts.");
                        crt = true;
                        Score += score;
                    } else if (userGuess < targetNumber) {
                        center("The number is higher than " + userGuess + ".");
                        score -= 10;
                    } else {
                        center("The number is lower than " + userGuess + ".");
                        score -= 10;
                    }
                }
                if (!crt) {
                    center("Sorry, you've used all lives. The number was: " + targetNumber);
                }
                center("Your score for this round: " + score + " / " + tot + "\n");
                box();
            }
            center("Game Over! Your total score is: " + Score + " / " + (tot * Rounds));
        }
        scanner.close();
    }
    public static void center(String str) {
        center(str, 0); // Default type is 0
    }
    public static void center(String str, int type) {
        int space = (total - str.length()) / 2, c = 0;
        StringBuilder cenText = new StringBuilder();
        cenText.append(" ".repeat(Math.max(0, space)));
        cenText.append(str);
        if(type == 1) System.out.print(cenText.toString());
        else System.out.println(cenText.toString());
    }
    public static void box() {
        StringBuilder cenText = new StringBuilder();
        cenText.append("-".repeat(Math.max(0, total)));
        System.out.println(cenText.toString());
    }
}