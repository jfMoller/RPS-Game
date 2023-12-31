package com.example.rpsgame.entity.characters;

import com.example.rpsgame.entity.choices.Choice;
import com.example.rpsgame.entity.stats.ChoiceObserver;
import com.example.rpsgame.entity.stats.ChoiceRecorder;


public abstract class GameCharacter {
    private final String name;

    protected ChoiceObserver choiceObserver;

    protected final ChoiceRecorder choiceRecorder;

    public GameCharacter(String name, ChoiceRecorder choiceRecorder) {
        this.name = name;
        this.choiceRecorder = choiceRecorder;
        this.addObserver(choiceRecorder);
        choiceObserver.recordCharacter(this);
    }

    public void addObserver(ChoiceObserver observer) {
        this.choiceObserver = observer;
    }

    public void notifyObserver(Choice newChoice) {
        choiceRecorder.recordChoice(newChoice);
    }

    public String getName() {
        return name;
    }

    public ChoiceRecorder getChoiceRecorder() {
        return choiceRecorder;
    }

    @Override
    public String toString() {
        return name;
    }
}
