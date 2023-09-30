package com.example.rpsgame.entity;


import com.example.rpsgame.dto.JsonFormatProvider;
import com.example.rpsgame.dto.JsonFormatProviderImpl;
import com.example.rpsgame.entity.characters.ComputerCharacter;
import com.example.rpsgame.entity.characters.PlayerCharacter;
import com.example.rpsgame.entity.choices.Choice;
import com.example.rpsgame.entity.rules.Result;
import com.example.rpsgame.entity.stats.ChoiceRecorder;
import com.example.rpsgame.entity.stats.MatchRecorder;
import com.example.rpsgame.entity.stats.StatisticsProvider;
import com.example.rpsgame.entity.stats.StatisticsProviderImpl;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Menu {

    private final Game game;
    private final StatisticsProvider statisticsProvider;

    private final JsonFormatProvider jsonFormatProvider;
    private final Scanner scanner = new Scanner(System.in);

    //Allowed range for the amount of rounds that can be played in the menu (MIN, MAX)
    private static final int MIN_ROUND_AMOUNT = 1;
    private static final int MAX_ROUND_AMOUNT = 10;

    public Menu(Game game) {
        this.game = game;
        this.statisticsProvider = new StatisticsProviderImpl();
        this.jsonFormatProvider = new JsonFormatProviderImpl();
    }

    public void render() {
        showDivider();
        showGreenHeader("Welcome to Rock Paper Scissors - a graded Java assignment");

        System.out.print("Please enter your name: ");
        // Prevents the player from using a blank or empty name
        String playerName = getValidUsername();

        /* Handles creation of the player & computer opponent characters,
           one of the opponent types ("Nomen") makes choices based on the player name,
           which is why the playerName argument is passed. */
        game.setUpGameCharacters(playerName);

        while (true) {
            showGameMenu();
            /* Prevents the player from choosing an option not available in the menu
               P = Play, S = Show statistics, Q = Quit */
            String menuChoice = getValidUserInput(List.of("P", "S", "Q"));

            if (menuChoice.equals("P")) {
                playGame();
            }

            if (menuChoice.equals("S")) {
                /* Shows stats in a JSON format which includes the choice ratio of the player and the different computer opponents,
                 *  as well as match results. */
                showAllStats(game.getCharacterChoiceRecorders(), game.getMatchRecorder());
            } else if (menuChoice.equals("Q")) {
                break;
            }
        }
    }

    private void playGame() {
        showChooseOpponent();
        String opponentChoice = getValidUserInput(List.of("P", "N", "T"));
        game.chooseComputerOpponent(opponentChoice);

        showChooseAmountOfRounds();
        // Allowed range of rounds is passed as (min, max) amount.
        int chosenAmount = getValidUserInput();
        game.chooseAmountOfRounds(chosenAmount);

        int roundCount = 1;
        do {
            playRound();
            roundCount++;
        } while (roundCount <= game.getAmountOfRounds());

        recordMatchResult();
        showMatchResults(game.getRoundResults().toString(), game.getMatchResult().toString());
        game.clearOldRoundResults();
    }

    private void recordMatchResult() {
        Result newMatchResult = game.getMatchResult();
        game.notifyObserver(newMatchResult);
    }

    private void playRound() {
        showRPSChoices();
        Choice playerChoice = parseStringToChoice(getValidUserInput(List.of("R", "P", "S")));

        PlayerCharacter player = game.getPlayer();
        player.notifyObserver(playerChoice);

        ComputerCharacter computerOpponent = game.getComputerOpponent();
        Choice computerChoice = game.getComputerOpponent().generateComputerChoice();
        computerOpponent.notifyObserver(computerChoice);

        Result newRoundResult = game.getRoundResult(playerChoice, computerChoice);
        recordRoundResult(newRoundResult);
        showRoundResult(player, playerChoice, computerOpponent, computerChoice, newRoundResult);
    }

    private void recordRoundResult(Result newRoundResult) {
        game.addRoundResult(newRoundResult);
    }

    private static Choice parseStringToChoice(String input) {
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
    }

    private void showChoiceStats(List<ChoiceRecorder> characterChoiceRecorders) {
        for (ChoiceRecorder characterChoices : characterChoiceRecorders) {
            String characterChoicesJson = statisticsProvider.getCharacterChoiceStats(characterChoices);
            jsonFormatProvider.printStyledJson(characterChoicesJson);
            showDivider();
        }
    }

    private void showMatchStats(MatchRecorder matchRecorder) {
        List<String> matches = statisticsProvider.getPlayerMatchStats(matchRecorder);
        if (matches.size() > 0) {
            for (String matchJson : matches) {
                jsonFormatProvider.printStyledJson(matchJson);
                showDivider();
            }
        } else {
            System.out.println("There are no match statistics available at this time.");
        }
    }

    private void showDivider() {
        System.out.println("\u001B[34m" + "-".repeat(150) + "\u001B[0m");
    }

    private void showGreenHeader(String text) {
        System.out.println("\u001B[34m" + "\u001B[32m" + text + "\u001B[0m");
        showDivider();
    }

    private void showRoundResult(PlayerCharacter player, Choice playerChoice, ComputerCharacter computerOpponent, Choice computerChoice, Result result) {
        showDivider();
        showGreenText(player.getName() + "'s choice: " + playerChoice);
        showGreenText(computerOpponent.getName() + "'s choice: " + computerChoice);
        showGreenText("Result: " + result);
    }

    private void showLightBlueHeader(String text) {
        showDivider();
        System.out.println("\u001B[36m" + text + "\u001B[0m");
    }

    private void showGameMenu() {
        showLightBlueHeader("Game menu:");
        System.out.println("\u001B[33m(P)\u001B[0mlay");
        System.out.println("\u001B[33m(S)\u001B[0mhow statistics");
        System.out.println("\u001B[33m(Q)\u001B[0muit");
    }

    private void showChooseOpponent() {
        showDivider();
        System.out.println("\u001B[36mChoose your opponent:\u001B[0m");
        showDivider();

        System.out.println("\u001B[35m(\u001B[33mP\u001B[0m\u001B[35m)assim\u001B[0m - Random Choices");
        System.out.println("\u001B[38;5;208m(\u001B[33mN\u001B[0m\u001B[38;5;208m)omen\u001B[0m - Choices based on your name");
        System.out.println("\u001B[31m(\u001B[33mT\u001B[0m\u001B[31m)empus\u001B[0m - Choices based on the time");
    }

    private void showRPSChoices() {
        showDivider();
        System.out.println("\u001B[36m" + "Select (\u001B[33mR\u001B[0m)ock, (\u001B[33mP\u001B[0m)aper or (\u001B[33mS\u001B[0m)cissors");
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
    }

    private String getValidUsername() {
        while (true) {
            String userInput = scanner.nextLine();
            if (!userInput.isBlank() && !userInput.isEmpty()) {
                return userInput;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private int getValidUserInput() {
        String minOption = Integer.toString(MIN_ROUND_AMOUNT);
        String maxOption = Integer.toString(MAX_ROUND_AMOUNT);
        while (true) {
            showHighlightedPrompt("Enter an integer between", List.of(minOption, maxOption));
            try {
                int userInput = Integer.parseInt(scanner.nextLine());
                if (userInput >= MIN_ROUND_AMOUNT && userInput <= MAX_ROUND_AMOUNT) {
                    return userInput;
                } else {
                    System.out.println("Invalid input. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    private String getValidUserInput(List<String> validOptions) {
        while (true) {
            showHighlightedPrompt("Enter your choice", validOptions);
            String userInput = scanner.nextLine().toUpperCase();
            if (validOptions.contains(userInput)) {
                return userInput;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private void showHighlightedPrompt(String prompt, List<String> options) {
        String formattedOptions = options.stream()
                .map("\u001B[33m%s\u001B[0m"::formatted)
                .collect(Collectors.joining(", "));
        showDivider();
        System.out.print(prompt + " (%s)".formatted(formattedOptions) + ": ");
    }

}
