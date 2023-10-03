package com.example.rpsgame;

import com.example.rpsgame.entity.Game;

import com.example.rpsgame.entity.Menu;


public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        Menu menu = new Menu(game);

        menu.render();
    }
}
