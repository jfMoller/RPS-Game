package com.example.rpsgame.javafx;

import com.example.rpsgame.GameApplication;
import com.example.rpsgame.dto.JsonFormatProvider;
import com.example.rpsgame.dto.JsonFormatProviderImpl;
import com.example.rpsgame.entity.Game;
import com.example.rpsgame.entity.stats.ChoiceRecorder;
import com.example.rpsgame.entity.stats.MatchRecorder;
import com.example.rpsgame.entity.stats.StatisticsProvider;
import com.example.rpsgame.entity.stats.StatisticsProviderImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

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

    @FXML
    private ListView<String> choiceListView;
    @FXML
    private VBox matchStatisticsVBox;


    public GameController() {
        this.game = new Game();
        this.gameApplication = new GameApplication();
        this.statisticsProvider = new StatisticsProviderImpl();
        this.jsonFormatProvider = new JsonFormatProviderImpl();

    }

    @FXML
    private void onSubmitButtonClick() throws IOException {
        this.playerName = playerNameField.getText();
        game.setUpGameCharacters(playerName);
        System.out.println(game.getPlayer().toString());
        gameApplication.renderMenuScene();
    }

    @FXML
    private void onShowStatisticsButtonClick() throws IOException {
        gameApplication.renderStatisticsScene();
    }

    private ObservableList<String> getChoiceItems() {
        ObservableList<String> choiceItems = FXCollections.observableArrayList();

        for (ChoiceRecorder characterChoices : game.getCharacterChoiceRecorders()) {
            String characterChoicesJson = statisticsProvider.getCharacterChoiceStats(characterChoices);
            choiceItems.add(characterChoicesJson);
        }

        return choiceItems;
    }

    private void populateMatchStatistics() {
        MatchRecorder matchRecorder = game.getMatchRecorder();
        List<String> matches = statisticsProvider.getPlayerMatchStats(matchRecorder);
        if (matches.size() > 0) {
            for (String matchJson : matches) {
                Label label = new Label(matchJson);
                matchStatisticsVBox.getChildren().add(label);
            }
        } else {
            Label label = new Label("There is no match statistics available at this time.");
            matchStatisticsVBox.getChildren().add(label);
        }
    }
}
