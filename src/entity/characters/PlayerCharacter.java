package entity.characters;

public class PlayerCharacter extends GameCharacter {

    public PlayerCharacter(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "PlayerCharacter{" +
                "name='" + name + '\'' +
                '}';
    }
}
