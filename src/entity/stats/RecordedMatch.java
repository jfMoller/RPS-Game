package entity.stats;

import entity.rules.Result;
import entity.characters.ComputerCharacter;
import entity.characters.PlayerCharacter;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class RecordedMatch {

    private static final AtomicLong matchCounter = new AtomicLong(1);

    private final long matchNumber;
    private PlayerCharacter player;

    private ComputerCharacter computer;

    private List<Result> roundResults;

    private Result matchResult;

    public RecordedMatch(PlayerCharacter player, ComputerCharacter computer, List<Result> roundResults, Result matchResult) {
        this.matchNumber = matchCounter.getAndIncrement();
        this.player = player;
        this.computer = computer;
        this.roundResults = roundResults;
        this.matchResult = matchResult;
    }

    @Override
    public String toString() {
        return "Match " + matchNumber + ": " +
                ", player: " + player +
                ", computer: " + computer +
                ", roundResults: " + roundResults +
                ", matchResult: " + matchResult +
                "\n";
    }
}
