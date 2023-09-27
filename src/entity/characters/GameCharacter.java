package entity.characters;

import entity.choices.Choice;
import entity.stats.ChoiceObserver;
import entity.stats.ChoiceRecorder;

public abstract class GameCharacter {
    String name;

    protected ChoiceObserver choiceObserver;

    // Note that choiceRecorder implements the interface ChoiceObserver
    protected final ChoiceRecorder choiceRecorder;

    public GameCharacter(String name, ChoiceRecorder choiceRecorder) {
        this.name = name;
        this.choiceRecorder = choiceRecorder;
        this.addObserver(choiceRecorder);
        choiceObserver.observeCharacter(this);
    }

    public void addObserver(ChoiceObserver observer) {
        choiceObserver = observer;
    }

    public void notifyObserver(Choice newChoice) {
        choiceRecorder.updateChoices(newChoice);
    }

    public String getName() {
        return name;
    }

    public ChoiceRecorder getChoiceRecorder() {
        return choiceRecorder;
    }

    @Override
    public String toString() {
        return "GameCharacter{" +
                "name='" + name + '\'' +
                '}';
    }
}
