package entity.characters;

public class GameCharacterFactory { // Generic factory method pattern

    public static PlayerCharacter createPlayerCharacter(String name) {
        return createCharacter(name, true);
    }

    public static ComputerCharacter createDefaultComputerCharacter() {
        return createCharacter("Passim", false);
    }

    public static NameBasedComputerCharacter createNameBasedComputerCharacter(String playerName) {
        return createCustomComputerCharacter("Nomen", playerName);
    }

    public static TimeBasedComputerCharacter createTimeBasedComputerCharacter() {
        return createCustomComputerCharacter("Tempus", null);
    }


    private static <T extends GameCharacter> T createCharacter(String name, boolean isPlayable) {
        if (isPlayable) {
            return (T) new PlayerCharacter(name);
        } else return (T) new ComputerCharacter(name);
    }

    private static <T extends ComputerCharacter> T createCustomComputerCharacter(String name, String playerName) {
        if (playerName != null) {
            return (T) new NameBasedComputerCharacter(name, playerName);
        } else return (T) new TimeBasedComputerCharacter(name);

    }
}
