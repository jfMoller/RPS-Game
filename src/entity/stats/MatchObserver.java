package entity.stats;

import entity.characters.ComputerCharacter;
import entity.characters.PlayerCharacter;
import entity.rules.Result;

import java.util.List;

public interface MatchObserver {

    void recordMatch(PlayerCharacter player, ComputerCharacter opponent, List<Result> roundResults, Result matchResult);

}
