package entity;

public class GameCharacterFactory { // Generic factory method pattern

    public static <T extends GameCharacter> T createCharacter(String name, boolean isPlayable) {
        if (isPlayable) {
            return (T) new PlayerCharacter(name);
        } else {
            return (T) new ComputerCharacter(name);
        }
    }
}
