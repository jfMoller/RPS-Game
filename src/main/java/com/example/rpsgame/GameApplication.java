package com.example.rpsgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameApplication extends Application {

    private final String title = "RPS-Game";

    private static Stage stage;

    public GameApplication() {
    }

    @Override
    public void start(Stage stage) throws IOException {
        GameApplication.stage = stage;
        renderStartScene();
    }

    public static void main(String[] args) {
        launch();
    }

    public void renderStartScene() throws IOException {
        setupScene(stage, "start-scene.fxml");
    }
    public void renderMenuScene() throws IOException {
        setupScene(stage, "menu-scene.fxml");
    }

    public void renderStatisticsScene() throws IOException {
        setupScene(stage, "show-statistics-scene.fxml");
    }

    public void renderGameScene() throws IOException {
        setupScene(stage, "setup-game-scene.fxml");
    }

    public void renderPlayGameScene() throws IOException {
        setupScene(stage, "play-game-scene.fxml");
    }

    public void setupScene(Stage stage, String sceneFileName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource(sceneFileName));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

}