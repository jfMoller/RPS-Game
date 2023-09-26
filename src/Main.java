import entity.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("""
                RPS Game
                - (S)tart game
                - (Q)uit
                """);
        System.out.println("Enter your choice (S, OR Q)");

        String choice = scanner.nextLine().toUpperCase();


        if (choice.equals("S")) {
            System.out.println("Enter your name:");
            String playerName = scanner.nextLine();

            System.out.println("Enter the amount of rounds:");
            int amountOfRounds = scanner.nextInt();

            PlayerCharacter player = GameCharacterFactory.createCharacter(playerName, true);
            ComputerCharacter computer = GameCharacterFactory.createCharacter("Computer", false);

            Game game = new Game(player, computer, amountOfRounds);

            game.playMatch();
        }

        else if (choice.equals("Q")) {
            return;
        }

    }
}
