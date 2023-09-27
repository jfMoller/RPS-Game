package entity.stats;

import entity.choices.Choice;

import java.util.List;

import static entity.choices.Choice.*;

public class StatisticsProvider {

    public static void showStatistics(List<ChoiceRecorder> characterChoiceRecorders, MatchRecorder matchRecorder) {

        System.out.println("Choice statistics:");
        for (ChoiceRecorder characterChoices : characterChoiceRecorders) {
            showCharacterChoiceStatistics(characterChoices);
        }
        System.out.println();
        System.out.println("Match statistics:");
        showPlayerMatchStatistics(matchRecorder);
    }

    public static void showPlayerMatchStatistics(MatchRecorder matchRecorder) {
        List<RecordedMatch> recordedMatches = matchRecorder.getRecordedMatches();
        System.out.println(recordedMatches);
    }

    public static void showCharacterChoiceStatistics(ChoiceRecorder characterChoiceRecorder) {
        RecordedGameCharacter recordedCharacter = characterChoiceRecorder.getRecordedGameCharacter();
        System.out.println(getChoicePercentages(recordedCharacter));
    }

    private static String getChoicePercentages(RecordedGameCharacter recordedGameCharacter) {
        double rockSum = 0;
        double paperSum = 0;
        double scissorsSum = 0;

        for (Choice choice : recordedGameCharacter.getChoices()) {
            if (choice.equals(ROCK)) rockSum++;

            if (choice.equals(PAPER)) paperSum++;

            if (choice.equals(SCISSORS)) scissorsSum++;
        }
        double totalSum = rockSum + paperSum + scissorsSum;

        double rockPercentage = 0;
        double paperPercentage = 0;
        double scissorsPercentage = 0;

        if (totalSum > 0) {
            rockPercentage = rockSum / totalSum * 100;
            paperPercentage = paperSum / totalSum * 100;
            scissorsPercentage = scissorsSum / totalSum * 100;
        }

        String characterName = recordedGameCharacter.getGameCharacter().getName();

        return "%-15s - ROCK: %-10.2f - PAPER: %-10.2f - SCISSORS: %-10.2f".formatted(characterName, rockPercentage, paperPercentage, scissorsPercentage);
    }

}
