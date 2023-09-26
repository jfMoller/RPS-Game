import entity.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Game game = new Game();
        MatchRecorder matchRecorder = new MatchRecorder();
        game.addObserver(matchRecorder);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to RPS");

        System.out.println("Enter your name:");
        String playerName = scanner.nextLine();

        PlayerCharacter player = GameCharacterFactory.createPlayerCharacter(playerName);
        game.setPlayer(player);

        while (true) {
            System.out.println("""
                    Game Menu
                    - (P)lay
                    - (Q)uit
                    """);
            System.out.println("Enter your choice (P, OR Q)");

            String choice = scanner.nextLine().toUpperCase();

            if (choice.equals("P")) {

                chooseOpponent(game, playerName);

                chooseAmountOfRounds(game);

                game.playMatch();

                List<RecordedMatch> recordedMatches = matchRecorder.getRecordedMatches();
                System.out.println(recordedMatches);
            } else if (choice.equals("Q")) {
                break;
            }
        }
        scanner.close();
    }

    public static void chooseOpponent(Game game, String playerName) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose your opponent:");
        System.out.println("(P)assim - Random Choices");
        System.out.println("(N)omen - Choices based on your name");
        System.out.println("(T)empus - Choices based on the time");
        System.out.println("Enter your choice (P, N or T)");

        String opponentChoice = scanner.nextLine();

        ComputerCharacter computer = null;
        if (opponentChoice.equalsIgnoreCase("P")) {
            computer = GameCharacterFactory.createDefaultComputerCharacter();
        }

        if (opponentChoice.equalsIgnoreCase("N")) {
            computer = GameCharacterFactory.createNameBasedComputerCharacter(playerName);
        }

        if (opponentChoice.equalsIgnoreCase("T")) {
            computer = GameCharacterFactory.createTimeBasedComputerCharacter();
        }
        game.setComputer(computer);
    }

    public static void chooseAmountOfRounds(Game game) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the amount of rounds:");
        int amountOfRounds = scanner.nextInt();
        game.setAmountOfRounds(amountOfRounds);
    }
}
