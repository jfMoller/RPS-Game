package entity;

import entity.characters.ComputerCharacter;
import entity.characters.PlayerCharacter;
import entity.choices.Choice;
import entity.rules.Result;
import entity.rules.Rules;
import entity.stats.MatchObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static entity.rules.Result.*;

public class Game {

    private final Rules rules = Rules.getInstance();
    ;
    private PlayerCharacter player;

    private ComputerCharacter computer;
    private double amountOfRounds;

    private List<Result> results = new ArrayList<>();

    private List<MatchObserver> observers = new ArrayList<>();

    public void addObserver(MatchObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(PlayerCharacter player, ComputerCharacter opponent, List<Result> roundResults, Result matchResult) {
        for (MatchObserver observer : observers) {
            observer.update(player, opponent, roundResults, matchResult);
        }
    }

    public Game() {
    }


    public void setPlayer(PlayerCharacter player) {
        this.player = player;
    }

    public void setComputer(ComputerCharacter computer) {
        this.computer = computer;
    }

    public void setAmountOfRounds(double amountOfRounds) {
        this.amountOfRounds = amountOfRounds;
    }

    public void playMatch() {
        Scanner scanner = new Scanner(System.in);

        int rounds = 1;
        do {
            System.out.println("Select (R)OCK, (P)APER or (S)cissors");

            Choice playerChoice = parseChoice(scanner.nextLine());
            player.notifyObserver(playerChoice);

            Choice computerChoice = computer.generateComputerChoice();
            computer.notifyObserver(computerChoice);

            Result roundResult = getRoundResult(playerChoice, computerChoice);
            results.add(roundResult);
            rounds++;
        } while (rounds <= amountOfRounds);

        Result matchResult = getMatchResult();

        notifyObservers(player, computer, results, matchResult);

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
