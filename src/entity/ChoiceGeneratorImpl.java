package entity;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class ChoiceGeneratorImpl implements ChoiceGenerator {

    private Random random = new Random();

    private final List<Choice> choices;

    public ChoiceGeneratorImpl(List<Choice> choices) {
        this.choices = choices;
    }

    @Override
    public Choice generateRandomChoice() {
        int randomIndex = random.nextInt(choices.size());
        return choices.get(randomIndex);
    }

    @Override
    public Choice generateNameBasedChoice(String playerName) {
        int sumOfAsciiValues = 0;

        for (char c : playerName.toCharArray()) {
            sumOfAsciiValues += c;
        }

        int choiceIndex = sumOfAsciiValues % 3;
        return choices.get(choiceIndex);
    }

    @Override
    public Choice generateTimeBasedChoice() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.SECOND);

        int choiceIndex;
        if (hour < 8) {
            choiceIndex = 0;
        } else if (hour < 16) {
            choiceIndex = 1;
        } else {
            choiceIndex = 2;
        }
        return choices.get(choiceIndex);
    }
}
