package entity.characters;

public abstract class GameCharacter {
    String name;

    public GameCharacter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "GameCharacter{" +
                "name='" + name + '\'' +
                '}';
    }
}
