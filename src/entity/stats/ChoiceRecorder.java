package entity.stats;

import entity.characters.GameCharacter;
import entity.choices.Choice;

public class ChoiceRecorder implements ChoiceObserver {

    private RecordedGameCharacter recordedGameCharacter;
    @Override
    public void recordCharacter(GameCharacter gameCharacter) {
        this.recordedGameCharacter = new RecordedGameCharacter(gameCharacter);
    }

    @Override
    public void recordChoice(Choice newChoice) {
        recordedGameCharacter.updateChoices(newChoice);
    }

    public RecordedGameCharacter getRecordedGameCharacter() {
        return recordedGameCharacter;
    }
}
