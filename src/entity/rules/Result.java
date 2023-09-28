package entity.rules;

public enum Result {
    TIE,
    PLAYER_WIN,
    COMPUTER_WIN,
    ;

    @Override
    public String toString() {
        return this.name();
    }
}


