package entity.rules;

import entity.choices.Choice;

import java.util.ArrayList;
import java.util.List;

import static entity.choices.Choice.*;

public class Rules {
    private final List<Choice> choices;

    private static final Rules instance = new Rules();

    public Rules() {
        this.choices = new ArrayList<>(List.of(ROCK, PAPER, SCISSORS));
    }

    public static Rules getInstance() {
        return instance;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public Result determineResult(Choice playerChoice, Choice computerChoice) {
        if (playerChoice.equals(computerChoice)) {
            return Result.TIE;
        } else if ((playerChoice.equals(Choice.ROCK) && computerChoice.equals(Choice.SCISSORS)) ||
                (playerChoice.equals(Choice.PAPER) && computerChoice.equals(Choice.ROCK)) ||
                (playerChoice.equals(Choice.SCISSORS) && computerChoice.equals(Choice.PAPER))) {
            return Result.PLAYER_WIN;
        } else {
            return Result.COMPUTER_WIN;
        }
    }

}
