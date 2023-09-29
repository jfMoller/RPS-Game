package entity;

import entity.characters.ComputerCharacter;
import entity.characters.GameCharacterFactory;
import entity.characters.PlayerCharacter;
import entity.choices.Choice;
import entity.rules.Result;
import entity.rules.Rules;
import entity.stats.ChoiceRecorder;
import entity.stats.MatchRecorder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static entity.rules.Result.*;

public class Game {

    private final Rules rules;

    private PlayerCharacter player;

    private ComputerCharacter computerOpponent;
    private final List<ComputerCharacter> computerOpponents = new ArrayList<>();

    private int amountOfRounds;

    private List<Result> roundResults = new ArrayList<>();

    private final MatchRecorder matchRecorder;


    public Game() {
        this.rules = Rules.getInstance();
        this.matchRecorder = new MatchRecorder();
    }

    public void notifyObserver(Result matchResult) {
        matchRecorder.recordMatch(player, computerOpponent, roundResults, matchResult);
    }

    public PlayerCharacter getPlayer() {
        return player;
    }

    public ComputerCharacter getComputerOpponent() {
        return computerOpponent;
    }

    public int getAmountOfRounds() {
        return amountOfRounds;
    }

    public List<Result> getRoundResults() {
        return roundResults;
    }

    public MatchRecorder getMatchRecorder() {
        return matchRecorder;
    }

    public List<ChoiceRecorder> getCharacterChoiceRecorders() {
        List<ChoiceRecorder> choiceRecorders = new ArrayList<>();

        choiceRecorders.add(player.getChoiceRecorder());
        for (ComputerCharacter opponent : computerOpponents) {
            choiceRecorders.add(opponent.getChoiceRecorder());
        }
        return choiceRecorders;
    }

    public void setPlayer(PlayerCharacter player) {
        this.player = player;
    }

    public void setComputerOpponent(ComputerCharacter computerOpponent) {
        this.computerOpponent = computerOpponent;
    }

    public void setAmountOfRounds(int amountOfRounds) {
        this.amountOfRounds = amountOfRounds;
    }

    public void setRoundResults(List<Result> roundResults) {
        this.roundResults = roundResults;
    }

    public void setUpGameCharacters(String playerName) {
        setupPlayer(playerName);
        generateComputerOpponents();
    }

    public void setupPlayer(String playerName) {
        this.setPlayer(GameCharacterFactory.createPlayerCharacter(playerName));
    }

    public void clearOldRoundResults() {
        setRoundResults(new ArrayList<>());
    }

    public void generateComputerOpponents() {
        ComputerCharacter passim = GameCharacterFactory.createDefaultComputerCharacter();
        computerOpponents.add(passim);

        ComputerCharacter nomen = GameCharacterFactory.createNameBasedComputerCharacter(player.getName());
        computerOpponents.add(nomen);

        ComputerCharacter tempus = GameCharacterFactory.createTimeBasedComputerCharacter();
        computerOpponents.add(tempus);
    }

    public void chooseComputerOpponent(String opponentChoice) {
        ComputerCharacter computerOpponent = null;

        switch (opponentChoice.toUpperCase()) {
            case "P" -> computerOpponent = findComputerOpponentByName("Passim");
            case "N" -> computerOpponent = findComputerOpponentByName("Nomen");
            case "T" -> computerOpponent = findComputerOpponentByName("Tempus");
        }
        this.setComputerOpponent(computerOpponent);
    }

    public ComputerCharacter findComputerOpponentByName(String name) {
        return computerOpponent = computerOpponents.stream()
                .filter(opponent -> opponent.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public void chooseAmountOfRounds(int chosenAmount) {
        setAmountOfRounds(chosenAmount);
    }

    public Result getRoundResult(Choice playerChoice, Choice computerChoice) {
        return rules.determineResult(playerChoice, computerChoice);
    }

    public void addRoundResult(Result roundResult) {
        roundResults.add(roundResult);
    }

    public Result getMatchResult() {
        int playerWinsAmount = Collections.frequency(roundResults, PLAYER_WIN);
        int computerWinsAmount = Collections.frequency(roundResults, COMPUTER_WIN);

        if (playerWinsAmount == computerWinsAmount) {
            return TIE;
        } else if (playerWinsAmount > computerWinsAmount) {
            return PLAYER_WIN;
        } else {
            return COMPUTER_WIN;
        }
    }
}
