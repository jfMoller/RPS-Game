package entity;

import java.util.List;

public interface MatchObserver {
    void update(PlayerCharacter player, ComputerCharacter opponent, List<Result> roundResults, Result matchResult);
}
