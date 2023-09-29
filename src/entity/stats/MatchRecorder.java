package entity.stats;

import entity.characters.ComputerCharacter;
import entity.characters.PlayerCharacter;
import entity.rules.Result;

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
