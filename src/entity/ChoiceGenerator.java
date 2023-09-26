package entity;

public interface ChoiceGenerator { // For strategy pattern
    Choice generateRandomChoice();

    Choice generateNameBasedChoice(String playerName);

    Choice generateTimeBasedChoice();
}
