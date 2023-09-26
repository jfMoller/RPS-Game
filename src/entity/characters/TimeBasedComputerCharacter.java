package entity.characters;

import entity.choices.Choice;

public class TimeBasedComputerCharacter extends ComputerCharacter {

    public TimeBasedComputerCharacter(String name) {
        super(name);
    }

    @Override
    public Choice generateComputerChoice() {
        return choiceGenerator.generateTimeBasedChoice();
    }
}
