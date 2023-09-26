package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static entity.Result.*;

public class Game {

    private final Rules rules;
    private PlayerCharacter player;

    private ComputerCharacter computer;
    private double amountOfRounds;

    private List<Result> results;

    public Game(PlayerCharacter player, ComputerCharacter computer, double amountOfRounds) {
        this.rules = Rules.getInstance();
        this.player = player;
        this.computer = computer;
        this.amountOfRounds = amountOfRounds;
        this.results = new ArrayList<>();
    }

    public void playMatch() {
        Scanner scanner = new Scanner(System.in);

        int rounds = 1;
        do {
            System.out.println("Select (R)OCK, (P)APER or (S)cissors");

            Choice playerChoice = parseChoice(scanner.nextLine());
            Choice computerChoice = computer.generateComputerChoice();

            Result roundResult = getRoundResult(playerChoice, computerChoice);
            results.add(roundResult);
            rounds++;
        } while (rounds <= amountOfRounds);

        Result matchResult = getMatchResult();

        System.out.println("_".repeat(30));
        System.out.println("Match result: " + matchResult);
        System.out.println("Round results: " + results);

    }

    private Choice parseChoice(String input) {
        return switch (input.toUpperCase()) {
            case "R" -> Choice.ROCK;
            case "P" -> Choice.PAPER;
            case "S" -> Choice.SCISSORS;
            default -> null;
        };
    }

    public Result getRoundResult(Choice playerChoice, Choice computerChoice) {
        Result result = rules.determineResult(playerChoice, computerChoice);
        showRoundResult(playerChoice, computerChoice, result);

        return result;
    }

    public void showRoundResult(Choice playerChoice, Choice computerChoice, Result result) {
        System.out.println(player.getName() + "'s choice: " + playerChoice + "\n"
                + computer.getName() + "'s choice: " + computerChoice + "\n"
                + "Result: " + result);
    }

    private Result getMatchResult() {
        int playerWinsAmount = 0;
        int computerWinsAmount = 0;
        for (Result result : results) {
            if (result.equals(PLAYER_WIN)) {
                playerWinsAmount++;
            } else if (result.equals(COMPUTER_WIN)) {
                computerWinsAmount++;
            }
        }


        if (playerWinsAmount == computerWinsAmount) {
            return TIE;
        } else if (playerWinsAmount > computerWinsAmount) {
            return PLAYER_WIN;
        } else {
            return COMPUTER_WIN;
        }
    }
}
