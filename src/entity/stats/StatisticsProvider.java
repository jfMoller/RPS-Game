package entity.stats;

import dto.JsonFormatProvider;
import dto.JsonFormatProviderImpl;
import entity.choices.Choice;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static entity.choices.Choice.*;

public interface StatisticsProvider {

    final JsonFormatProvider jsonFormatProvider = new JsonFormatProviderImpl();

    List<String> getPlayerMatchStats(MatchRecorder matchRecorder);

    default List<String> formatPlayerMatchStats(MatchRecorder matchRecorder) {
        List<String> matches = new ArrayList<>();
        for (RecordedMatch match : matchRecorder.getRecordedMatches()) {
            matches.add(getJsonFormatForPlayerMatch(match));
        }
        return matches;
    }

    private String getJsonFormatForPlayerMatch(RecordedMatch match) {

        Map<String, Object> items = new LinkedHashMap<>();
        items.put("matchNumber", match.getMatchNumber());
        items.put("player", match.getPlayer().getName());
        items.put("computerOpponent", match.getComputerOpponent().getName());
        items.put("roundResults", match.getRoundResults().stream().map(result -> "\"" + result.toString() + "\"").toList());
        items.put("matchResult", match.getMatchResult().toString());

        return jsonFormatProvider.getJsonFormat(items);
    }

    String getCharacterChoiceStats(ChoiceRecorder characterChoiceRecorder);

    default String calculateChoicePercentages(RecordedGameCharacter recordedGameCharacter) {
        double rockSum = 0;
        double paperSum = 0;
        double scissorsSum = 0;

        for (Choice choice : recordedGameCharacter.getChoices()) {
            if (choice.equals(ROCK)) rockSum++;
            if (choice.equals(PAPER)) paperSum++;
            if (choice.equals(SCISSORS)) scissorsSum++;
        }

        double totalSum = rockSum + paperSum + scissorsSum;

        // Used to maintain decimals (.2) in JSON format
        BigDecimal rockPercentage = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        BigDecimal paperPercentage = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        BigDecimal scissorsPercentage = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

        if (totalSum > 0) {
            rockPercentage = BigDecimal.valueOf(rockSum / totalSum * 100).setScale(2, RoundingMode.HALF_UP);
            paperPercentage = BigDecimal.valueOf(paperSum / totalSum * 100).setScale(2, RoundingMode.HALF_UP);
            scissorsPercentage = BigDecimal.valueOf(scissorsSum / totalSum * 100).setScale(2, RoundingMode.HALF_UP);
        }

        return getJsonFormatForChoicePercentages(recordedGameCharacter, rockPercentage, paperPercentage, scissorsPercentage);

    }

    private String getJsonFormatForChoicePercentages(
            RecordedGameCharacter recordedGameCharacter,
            BigDecimal rockPercentage,
            BigDecimal paperPercentage, BigDecimal scissorsPercentage) {
        String characterName = recordedGameCharacter.getGameCharacter().getName();

        Map<String, Object> items = new LinkedHashMap<>();
        items.put("name", characterName);
        items.put("rockPercentage", rockPercentage);
        items.put("paperPercentage", paperPercentage);
        items.put("scissorsPercentage", scissorsPercentage);

        return jsonFormatProvider.getJsonFormat(items);
    }
}
