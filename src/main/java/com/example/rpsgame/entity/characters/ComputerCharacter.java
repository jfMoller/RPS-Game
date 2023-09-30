package com.example.rpsgame.entity.characters;


import com.example.rpsgame.entity.choices.Choice;
import com.example.rpsgame.entity.choices.ChoiceGenerator;
import com.example.rpsgame.entity.choices.ChoiceGeneratorImpl;
import com.example.rpsgame.entity.rules.Rules;
import com.example.rpsgame.entity.stats.ChoiceRecorder;


public class ComputerCharacter extends GameCharacter {

    protected final ChoiceGenerator choiceGenerator;

    public ComputerCharacter(String name) {
        super(name, new ChoiceRecorder());
        this.choiceGenerator = new ChoiceGeneratorImpl(Rules.getInstance().getChoices());
    }

    public Choice generateComputerChoice() {
        return choiceGenerator.generateRandomChoice();
    }

}