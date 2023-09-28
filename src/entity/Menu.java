package entity;

import entity.characters.ComputerCharacter;
import entity.characters.PlayerCharacter;
import entity.choices.Choice;
import entity.rules.Result;
import entity.stats.ChoiceRecorder;
import entity.stats.MatchRecorder;
import entity.stats.StatisticsProvider;
import entity.stats.StatisticsProviderImpl;

import java.util.List;
import java.util.Scanner;

public class Menu {

    Game game;
    StatisticsProvider statisticsProvider;
    private final Scanner scanner = new Scanner(System.in);

    public Menu(Game game) {
        this.game = game;
        this.statisticsProvider = new StatisticsProviderImpl();
    }

    public void render() {
        showGreenHeader("Welcome to Rock Paper Scissors - a graded fullstack developer assignment");
        System.out.println("Please enter your name:");
        showDivider();

        String playerName = scanner.nextLine();
        game.setUpGameCharacters(playerName);

        while (true) {
            showGameMenu();
            String menuChoice = scanner.nextLine().toUpperCase();

            if (menuChoice.equals("P")) {
                playGame();
            }

            if (menuChoice.equals("S")) {
                showAllStats(game.getCharacterChoiceRecorders(), game.getMatchRecorder());
            } else if (menuChoice.equals("Q")) {
                break;
            }
        }
    }

    private void playGame() {
        showChooseOpponent();
        String opponentChoice = scanner.nextLine();
        game.chooseComputerOpponent(opponentChoice);

        showChooseAmountOfRounds();
        int chosenAmount = scanner.nextInt();
        scanner.nextLine();
        game.chooseAmountOfRounds(chosenAmount);

        int rounds = 1;
        do {
            playRound();
            rounds++;
        } while (rounds <= game.getAmountOfRounds());

        showMatchResults(game.getRoundResults().toString(), game.getMatchResult().toString());
    }

    private void playRound() {
        showRPSChoice();
        Choice playerChoice = parseInputChoice(scanner.nextLine());

        if (playerChoice != null) {
            PlayerCharacter player = game.getPlayer();
            player.notifyObserver(playerChoice);

            ComputerCharacter computerOpponent = game.getComputerOpponent();
            Choice computerChoice = game.getComputerOpponent().generateComputerChoice();
            computerOpponent.notifyObserver(computerChoice);

            Result newRoundResult = game.getRoundResult(playerChoice, computerChoice);
            game.addRoundResult(newRoundResult);

            showRoundResult(player, playerChoice, computerOpponent, computerChoice, newRoundResult);
        }
    }

    private static Choice parseInputChoice(String input) {
        return switch (input.toUpperCase()) {
            case "R" -> Choice.ROCK;
            case "P" -> Choice.PAPER;
            case "S" -> Choice.SCISSORS;
            default -> throw new RuntimeException("Wrong input");
        };
    }

    public void showAllStats(List<ChoiceRecorder> characterChoiceRecorders, MatchRecorder matchRecorder) {
        showGreenHeader("Choice statistics:");
        showChoiceStats(characterChoiceRecorders);

        showGreenHeader("Match statistics:");
        showMatchStats(matchRecorder);
        showDivider();
    }

    private void showChoiceStats(List<ChoiceRecorder> characterChoiceRecorders) {
        for (ChoiceRecorder characterChoices : characterChoiceRecorders) {
            System.out.println("    " + (statisticsProvider.getCharacterChoiceStats(characterChoices)));
        }
    }

    private void showMatchStats(MatchRecorder matchRecorder) {
        List<String> matches = statisticsProvider.getPlayerMatchStats(matchRecorder);
        if (matches.size() > 0) {
            for (String match : matches) {
                System.out.println("    " + match);
            }
        } else {
            System.out.println("    There are no match statistics available at this time.");
        }
    }

    private void showDivider() {
        System.out.println("\u001B[34m" + "-".repeat(150) + "\u001B[0m");
    }

    private void showGreenHeader(String text) {
        showDivider();
        System.out.println("\u001B[34m" + "\u001B[32m" + text + "\u001B[0m");
        showDivider();
    }

    private void showRoundResult(PlayerCharacter player, Choice playerChoice, ComputerCharacter computerOpponent, Choice computerChoice, Result result) {
        showDivider();
        showGreenText(player.getName() + "'s choice: " + playerChoice);
        showGreenText(computerOpponent.getName() + "'s choice: " + computerChoice);
        showGreenText("Result: " + result);
        showDivider();
    }

    private void showLightBlueHeader(String text) {
        showDivider();
        System.out.println("\u001B[36m" + text + "\u001B[0m");
        showDivider();
    }

    private void showGameMenu() {
        showLightBlueHeader("Game menu:");
        System.out.println("    \u001B[33m(P)\u001B[0mlay");
        System.out.println("    \u001B[33m(S)\u001B[0mhow statistics");
        System.out.println("    \u001B[33m(Q)\u001B[0muit");

        showDivider();
        System.out.println("Enter your choice:(\u001B[33mP\u001B[0m, \u001B[33mS\u001B[0m or \u001B[33mQ\u001B[0m)");
        showDivider();
    }

    private void showChooseOpponent() {
        showDivider();
        System.out.println("\u001B[36mChoose your opponent:\u001B[0m");
        showDivider();

        System.out.println("\u001B[35m(P)assim\u001B[0m - Random Choices");
        System.out.println("\u001B[38;5;208m(N)omen\u001B[0m - Choices based on your name");
        System.out.println("\u001B[31m(T)empus\u001B[0m - Choices based on the time");
        showDivider();
        System.out.println("Enter your choice (\u001B[35mP\u001B[0m, \u001B[38;5;208mN\u001B[0m or \u001B[31mT\u001B[0m)");
        showDivider();
    }

    private void showRPSChoice() {
        showDivider();
        System.out.println("\u001B[36m" + "Select (\u001B[33mR\u001B[0m)ock, (\u001B[33mP\u001B[0m)aper or (\u001B[33mS\u001B[0m)cissors");
        showDivider();
    }

    private void showChooseAmountOfRounds() {
        showLightBlueHeader("Enter the amount of rounds:");
    }

    private void showGreenText(String text) {
        System.out.println("\u001B[32m" + text + "\u001B[0m");
    }

    public void showMatchResults(String roundResults, String newMatchResult) {
        showLightBlueHeader("Match result:");
        showGreenText(newMatchResult);
        showLightBlueHeader("Round results:");
        showGreenText(roundResults);
        showDivider();
    }
}
