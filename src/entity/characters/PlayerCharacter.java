package entity.characters;

import entity.stats.ChoiceRecorder;

public class PlayerCharacter extends GameCharacter {


    public PlayerCharacter(String name) {
        super(name, new ChoiceRecorder());
    }

    @Override
    public String toString() {
        return "PlayerCharacter{" +
                "name='" + name + '\'' +
                '}';
    }
}
