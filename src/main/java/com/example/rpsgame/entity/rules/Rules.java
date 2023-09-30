package com.example.rpsgame.entity.rules;


import com.example.rpsgame.entity.choices.Choice;

import java.util.ArrayList;
import java.util.List;

import static com.example.rpsgame.entity.choices.Choice.*;


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
        } else if ((playerChoice.equals(ROCK) && computerChoice.equals(SCISSORS)) ||
                (playerChoice.equals(PAPER) && computerChoice.equals(ROCK)) ||
                (playerChoice.equals(SCISSORS) && computerChoice.equals(PAPER))) {
            return Result.PLAYER_WIN;
        } else {
            return Result.COMPUTER_WIN;
        }
    }

}
