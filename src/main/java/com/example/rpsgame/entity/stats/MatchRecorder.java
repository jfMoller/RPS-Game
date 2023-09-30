package com.example.rpsgame.entity.stats;

import com.example.rpsgame.entity.characters.ComputerCharacter;
import com.example.rpsgame.entity.characters.PlayerCharacter;
import com.example.rpsgame.entity.rules.Result;

import java.util.ArrayList;
import java.util.List;

public class MatchRecorder implements MatchObserver {

    private List<RecordedMatch> recordedMatches = new ArrayList<>();

    @Override
    public void recordMatch(PlayerCharacter player, ComputerCharacter opponent, List<Result> roundResults, Result matchResult) {

        recordedMatches.add(new RecordedMatch(player, opponent, roundResults, matchResult));
    }

    public List<RecordedMatch> getRecordedMatches() {
        return recordedMatches;
    }

}
