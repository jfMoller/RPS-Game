package entity;

public class NameBasedComputerCharacter extends ComputerCharacter {

    private String playerName;

    public NameBasedComputerCharacter(String name, String playerName) {
        super(name);
        this.playerName = playerName;
    }

    @Override
    public Choice generateComputerChoice() {
        return choiceGenerator.generateNameBasedChoice(playerName);
    }
}
