package entity.stats;

import entity.characters.GameCharacter;
import entity.choices.Choice;

public interface ChoiceObserver {

    void observeCharacter(GameCharacter gameCharacter);
    void updateChoices(Choice newChoice);
}
