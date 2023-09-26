package entity;

import java.util.List;
import java.util.Random;

public class RandomChoiceGenerator implements ChoiceGenerator {

    private Random random = new Random();

    private final List<Choice> choices;

    public RandomChoiceGenerator(List<Choice> choices) {
        this.choices = choices;
    }

    @Override
    public Choice generateChoice() {
        int randomIndex = random.nextInt(choices.size());
        return choices.get(randomIndex);
    }
}
