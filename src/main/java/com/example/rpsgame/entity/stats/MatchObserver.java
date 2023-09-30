package com.example.rpsgame.entity.stats;

import com.example.rpsgame.entity.characters.ComputerCharacter;
import com.example.rpsgame.entity.characters.PlayerCharacter;
import com.example.rpsgame.entity.rules.Result;

import java.util.List;


public interface MatchObserver {

    void recordMatch(PlayerCharacter player, ComputerCharacter opponent, List<Result> roundResults, Result matchResult);

}
