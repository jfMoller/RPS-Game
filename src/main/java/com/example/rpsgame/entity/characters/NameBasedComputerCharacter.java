package com.example.rpsgame.entity.characters;


import com.example.rpsgame.entity.choices.Choice;

public class NameBasedComputerCharacter extends ComputerCharacter {

    private final String playerName;

    public NameBasedComputerCharacter(String name, String playerName) {
        super(name);
        this.playerName = playerName;
    }

    @Override
    public Choice generateComputerChoice() {
        return choiceGenerator.generateNameBasedChoice(playerName);
    }
}
