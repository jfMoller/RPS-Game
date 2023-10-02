package com.example.rpsgame.javafx;

import com.example.rpsgame.GameApplication;
import com.example.rpsgame.dto.JsonFormatProvider;
import com.example.rpsgame.dto.JsonFormatProviderImpl;
import com.example.rpsgame.entity.Game;
import com.example.rpsgame.entity.stats.StatisticsProvider;
import com.example.rpsgame.entity.stats.StatisticsProviderImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class GameController {

    Game game;
    GameApplication gameApplication;

    StatisticsProvider statisticsProvider;

    JsonFormatProvider jsonFormatProvider;


    String playerName;

    @FXML
    protected TextField playerNameField;

    @FXML
    protected Button submitButton;

    public GameController() {
        this.game = new Game();
        this.gameApplication = new GameApplication();
        this.statisticsProvider = new StatisticsProviderImpl();
        this.jsonFormatProvider = new JsonFormatProviderImpl();

    }

    @FXML
    private void onSubmitButtonClick() throws IOException {
        this.playerName = playerNameField.getText();
        System.out.println(playerName);
        gameApplication.showMenuScene();
    }
}
