import entity.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to RPS");

        System.out.println("Enter your name:");
        String playerName = scanner.nextLine();

        System.out.println("Enter the amount of rounds:");
        int amountOfRounds = scanner.nextInt();

        PlayerCharacter player = GameCharacterFactory.createCharacter(playerName, true);
        ComputerCharacter computer = GameCharacterFactory.createCharacter("Computer", false);

        Game game = new Game(player, computer, amountOfRounds);

        MatchRecorder matchRecorder = new MatchRecorder();
        game.addObserver(matchRecorder);

        while (true) {
            System.out.println("""
                    Game Menu
                    - (P)lay
                    - (Q)uit
                    """);
            System.out.println("Enter your choice (P, OR Q)");

            String choice = scanner.nextLine().toUpperCase();

            if (choice.equals("P")) {
                game.playMatch();
                List<RecordedMatch> recordedMatches = matchRecorder.getRecordedMatches();
                System.out.println(recordedMatches);
            } else if (choice.equals("Q")) {
                break;
            }
        }
    }
}
