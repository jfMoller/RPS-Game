package com.example.rpsgame.entity.characters;


import com.example.rpsgame.entity.choices.Choice;

public class TimeBasedComputerCharacter extends ComputerCharacter {

    public TimeBasedComputerCharacter(String name) {
        super(name);
    }

    @Override
    public Choice generateComputerChoice() {
        return choiceGenerator.generateTimeBasedChoice();
    }
}
