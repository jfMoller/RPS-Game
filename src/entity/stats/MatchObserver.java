package entity.stats;

import entity.rules.Result;
import entity.characters.ComputerCharacter;
import entity.characters.PlayerCharacter;

import java.util.List;

public interface MatchObserver {
    void update(PlayerCharacter player, ComputerCharacter opponent, List<Result> roundResults, Result matchResult);
}
