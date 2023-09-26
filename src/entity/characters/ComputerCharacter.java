package entity.characters;

import entity.rules.Rules;
import entity.choices.Choice;
import entity.choices.ChoiceGenerator;
import entity.choices.ChoiceGeneratorImpl;

public class ComputerCharacter extends GameCharacter {

    protected final ChoiceGenerator choiceGenerator;

    public ComputerCharacter(String name) {
        super(name);
        this.choiceGenerator = new ChoiceGeneratorImpl(Rules.getInstance().getChoices());
    }

    public Choice generateComputerChoice() {
        return choiceGenerator.generateRandomChoice();
    }

    @Override
    public String toString() {
        return "ComputerCharacter{" +
                "name='" + name + '\'' +
                '}';
    }

}