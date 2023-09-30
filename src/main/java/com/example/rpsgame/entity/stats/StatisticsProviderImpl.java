package com.example.rpsgame.entity.stats;

import java.util.List;

public class StatisticsProviderImpl implements StatisticsProvider {

    @Override
    public List<String> getPlayerMatchStats(MatchRecorder matchRecorder) {
        return formatPlayerMatchStats(matchRecorder);
    }

    @Override
    public List<String> formatPlayerMatchStats(MatchRecorder matchRecorder) {
        return StatisticsProvider.super.formatPlayerMatchStats(matchRecorder);
    }

    @Override
    public String getCharacterChoiceStats(ChoiceRecorder characterChoiceRecorder) {
        RecordedGameCharacter recordedCharacter = characterChoiceRecorder.getRecordedGameCharacter();
        return calculateChoicePercentages(recordedCharacter);
    }

    @Override
    public String calculateChoicePercentages(RecordedGameCharacter recordedGameCharacter) {
        return StatisticsProvider.super.calculateChoicePercentages(recordedGameCharacter);
    }
}
