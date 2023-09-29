package entity.choices;

public interface ChoiceGenerator {
    Choice generateRandomChoice();

    Choice generateNameBasedChoice(String playerName);

    Choice generateTimeBasedChoice();
}
