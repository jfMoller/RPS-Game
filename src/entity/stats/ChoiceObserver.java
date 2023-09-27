package entity.stats;

import entity.characters.GameCharacter;
import entity.choices.Choice;

public interface ChoiceObserver {

    void recordCharacter(GameCharacter newGameCharacter);
    void recordChoice(Choice newChoice);
}
