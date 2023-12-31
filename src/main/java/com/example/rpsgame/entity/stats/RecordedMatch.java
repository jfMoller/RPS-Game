package com.example.rpsgame.entity.stats;

import com.example.rpsgame.entity.characters.ComputerCharacter;
import com.example.rpsgame.entity.characters.PlayerCharacter;
import com.example.rpsgame.entity.rules.Result;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class RecordedMatch {

    private static final AtomicLong matchCounter = new AtomicLong(1);

    private final long matchNumber;
    private PlayerCharacter player;

    private ComputerCharacter computerOpponent;

    private List<Result> roundResults;

    private Result matchResult;

    public RecordedMatch(PlayerCharacter player, ComputerCharacter computerOpponent, List<Result> roundResults, Result matchResult) {
        this.matchNumber = matchCounter.getAndIncrement();
        this.player = player;
        this.computerOpponent = computerOpponent;
        this.roundResults = roundResults;
        this.matchResult = matchResult;
    }

    public long getMatchNumber() {
        return matchNumber;
    }

    public PlayerCharacter getPlayer() {
        return player;
    }

    public ComputerCharacter getComputerOpponent() {
        return computerOpponent;
    }

    public List<Result> getRoundResults() {
        return roundResults;
    }

    public Result getMatchResult() {
        return matchResult;
    }

}
