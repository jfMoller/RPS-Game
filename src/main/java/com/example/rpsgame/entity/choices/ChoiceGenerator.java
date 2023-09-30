package com.example.rpsgame.entity.choices;

public interface ChoiceGenerator {
    Choice generateRandomChoice();

    Choice generateNameBasedChoice(String playerName);

    Choice generateTimeBasedChoice();
}
