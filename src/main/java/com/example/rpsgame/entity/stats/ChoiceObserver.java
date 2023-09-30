package com.example.rpsgame.entity.stats;

import com.example.rpsgame.entity.characters.GameCharacter;
import com.example.rpsgame.entity.choices.Choice;

public interface ChoiceObserver {

    void recordCharacter(GameCharacter newGameCharacter);
    void recordChoice(Choice newChoice);
}
