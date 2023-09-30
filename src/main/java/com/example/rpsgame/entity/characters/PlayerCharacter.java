package com.example.rpsgame.entity.characters;
import com.example.rpsgame.entity.stats.ChoiceRecorder;

public class PlayerCharacter extends GameCharacter {

    public PlayerCharacter(String name) {
        super(name, new ChoiceRecorder());
    }

}
