package entity;

import entity.characters.ComputerCharacter;
import entity.characters.GameCharacterFactory;
import entity.characters.PlayerCharacter;
import entity.choices.Choice;
import entity.rules.Result;
import entity.rules.Rules;
import entity.stats.ChoiceRecorder;
import entity.stats.MatchObserver;
import entity.stats.MatchRecorder;

import java.util.ArrayList;
import java.util.List;

import static entity.rules.Result.*;

public class Game {

    private final Rules rules = Rules.getInstance();
    ;
    private PlayerCharacter player;

    private ComputerCharacter computerOpponent;
    private List<ComputerCharacter> computerOpponents = new ArrayList<>();

    private int amountOfRounds;

    private List<Result> roundResults = new ArrayList<>();

    private MatchObserver matchObserver;

    private final MatchRecorder matchRecorder;


    public Game() {
        this.matchRecorder = new MatchRecorder();
        this.addObserver(matchRecorder);
    }

    public void addObserver(MatchObserver observer) {
        matchObserver = observer;
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
        return List.of(
                player.getChoiceRecorder(),
                findComputerOpponentByName("Passim").getChoiceRecorder(),
                findComputerOpponentByName("Nomen").getChoiceRecorder(),
                findComputerOpponentByName("Tempus").getChoiceRecorder());
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

    public void setUpGameCharacters(String playerName) {
        setupPlayer(playerName);
        generateComputerOpponents();
    }

    public void setupPlayer(String playerName) {
        this.setPlayer(GameCharacterFactory.createPlayerCharacter(playerName));
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
        int playerWinsAmount = 0;
        int computerWinsAmount = 0;
        for (Result roundResult : roundResults) {
            if (roundResult.equals(PLAYER_WIN)) {
                playerWinsAmount++;
            } else if (roundResult.equals(COMPUTER_WIN)) {
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
