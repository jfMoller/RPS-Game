import entity.Game;
import entity.characters.ComputerCharacter;
import entity.characters.GameCharacterFactory;
import entity.characters.PlayerCharacter;
import entity.stats.ChoiceRecorder;
import entity.stats.MatchRecorder;
import entity.stats.RecordedGameCharacter;
import entity.stats.RecordedMatch;

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

        // Instanced opponents (to set up observers from the jump)
        ComputerCharacter passim = GameCharacterFactory.createDefaultComputerCharacter();

        ComputerCharacter nomen = GameCharacterFactory.createNameBasedComputerCharacter(playerName);

        ComputerCharacter tempus = GameCharacterFactory.createTimeBasedComputerCharacter();

        while (true) {
            System.out.println("""
                    Game Menu
                    - (P)lay
                    - (S)how statistics
                    - (Q)uit
                    """);
            System.out.println("Enter your choice (P, S or Q)");

            String choice = scanner.nextLine().toUpperCase();

            if (choice.equals("P")) {

                chooseOpponent(game, passim, nomen, tempus);

                chooseAmountOfRounds(game);

                game.playMatch();

            }

            if (choice.equals("S")) {
                showStatistics(
                        player.getChoiceRecorder(),
                        passim.getChoiceRecorder(),
                        nomen.getChoiceRecorder(),
                        tempus.getChoiceRecorder(),
                        matchRecorder);
            } else if (choice.equals("Q")) {
                break;
            }
        }
        scanner.close();
    }

    public static void chooseOpponent(Game game,
                                      ComputerCharacter passim,
                                      ComputerCharacter nomen,
                                      ComputerCharacter tempus) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose your opponent:");
        System.out.println("(P)assim - Random Choices");
        System.out.println("(N)omen - Choices based on your name");
        System.out.println("(T)empus - Choices based on the time");
        System.out.println("Enter your choice (P, N or T)");

        String opponentChoice = scanner.nextLine();

        ComputerCharacter computerOpponent = null;
        if (opponentChoice.equalsIgnoreCase("P")) {
            computerOpponent = passim;
        }

        if (opponentChoice.equalsIgnoreCase("N")) {
            computerOpponent = nomen;
        }

        if (opponentChoice.equalsIgnoreCase("T")) {
            computerOpponent = tempus;
        }
        game.setComputer(computerOpponent);
    }

    public static void chooseAmountOfRounds(Game game) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the amount of rounds:");
        int amountOfRounds = scanner.nextInt();
        game.setAmountOfRounds(amountOfRounds);
    }

    public static void showStatistics(ChoiceRecorder playerChoiceRecorder,
                                      ChoiceRecorder passimChoiceRecorder,
                                      ChoiceRecorder nomenChoiceRecorder,
                                      ChoiceRecorder tempusChoiceRecorder,
                                      MatchRecorder matchRecorder) {

        showCharacterChoiceStatistics(playerChoiceRecorder);
        showCharacterChoiceStatistics(passimChoiceRecorder);
        showCharacterChoiceStatistics(nomenChoiceRecorder);
        showCharacterChoiceStatistics(tempusChoiceRecorder);
        showPlayerMatchStatistics(matchRecorder);
    }

    public static void showPlayerMatchStatistics(MatchRecorder matchRecorder) {
        List<RecordedMatch> recordedMatches = matchRecorder.getRecordedMatches();
        System.out.println(recordedMatches);
    }

    public static void showCharacterChoiceStatistics(ChoiceRecorder characterChoiceRecorder) {
        RecordedGameCharacter recordedCharacter = characterChoiceRecorder.getRecordedGameCharacter();
        System.out.println(recordedCharacter);
    }
}
