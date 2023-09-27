package entity.stats;

import entity.characters.GameCharacter;
import entity.choices.Choice;

import java.util.ArrayList;
import java.util.List;

public class RecordedGameCharacter {

    private final GameCharacter gameCharacter;

    private List<Choice> choices;

    public RecordedGameCharacter(GameCharacter gameCharacter) {
        this.gameCharacter = gameCharacter;
        this.choices = new ArrayList<>();
    }

    public void updateChoices(Choice newChoice) {
        choices.add(newChoice);
    }

    public GameCharacter getGameCharacter() {
        return gameCharacter;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    @Override
    public String toString() {
        return "%-15s %-15s".formatted(gameCharacter, choices);
    }
}
