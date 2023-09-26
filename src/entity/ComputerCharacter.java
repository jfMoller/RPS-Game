package entity;

public class ComputerCharacter extends GameCharacter {

private final ChoiceGenerator choiceGenerator;
    public ComputerCharacter(String name) {
        super(name);
        this.choiceGenerator = new RandomChoiceGenerator(Rules.getInstance().getChoices());
    }

    public Choice generateComputerChoice() {
       return choiceGenerator.generateChoice();
    }
}
