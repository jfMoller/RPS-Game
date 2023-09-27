package entity;

import entity.choices.Choice;
import entity.rules.Result;
import entity.stats.StatisticsProvider;

import java.util.Scanner;

public class Menu {

    Game game;

    public Menu(Game game) {
        this.game = game;
    }

    public void launch() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to RPS");


        System.out.println("Enter your name:");
        String playerName = scanner.nextLine();

        game.setUpGameCharacters(playerName);

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
                System.out.println("""
                        Choose your opponent
                        (P)assim - Random Choices
                        (N)omen - Choices based on your name
                        (T)empus - Choices based on the time
                        Enter your choice (P, N or T)""");

                String opponentChoice = scanner.nextLine();

                game.chooseComputerOpponent(opponentChoice);

                System.out.println("Enter the amount of rounds:");
                int chosenAmount = scanner.nextInt();
                scanner.nextLine();
                game.chooseAmountOfRounds(chosenAmount);

                int rounds = 1;
                do {
                    System.out.println("Select (R)OCK, (P)APER or (S)cissors");

                    Choice playerChoice = parseChoice(scanner.nextLine());

                    if (playerChoice != null) {
                        game.getPlayer().notifyObserver(playerChoice);

                        Choice computerChoice = game.getComputerOpponent().generateComputerChoice();
                        game.getComputerOpponent().notifyObserver(computerChoice);

                        Result roundResult = game.getRoundResult(playerChoice, computerChoice);
                        game.addRoundResult(roundResult);
                    }

                    rounds++;
                } while (rounds <= game.getAmountOfRounds());

                Result matchResult = game.getMatchResult();

                game.notifyObserver(matchResult);

                System.out.println("_".repeat(30));
                System.out.println("Match result: " + matchResult);
                System.out.println("Round results: " + game.getRoundResults());

            }

            if (choice.equals("S")) {
                StatisticsProvider.showStatistics(game.getCharacterChoiceRecorders(), game.getMatchRecorder());
            } else if (choice.equals("Q")) {
                break;
            }
        }
    }

    private static Choice parseChoice(String input) {
        return switch (input.toUpperCase()) {
            case "R" -> Choice.ROCK;

            case "P" -> Choice.PAPER;

            case "S" -> Choice.SCISSORS;

            default -> throw new RuntimeException("Wrong input");

        };
    }
}


