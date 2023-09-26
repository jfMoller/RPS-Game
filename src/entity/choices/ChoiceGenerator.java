package entity.choices;

public interface ChoiceGenerator { // For strategy pattern
    Choice generateRandomChoice();

    Choice generateNameBasedChoice(String playerName);

    Choice generateTimeBasedChoice();
}
