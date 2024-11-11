package com.example.demo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController {
    public Button button1;
    public Button button2;
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void startGame() {
        GameController myGameController = new GameController(stage);
        myGameController.launchGame();
    }

    @FXML
    private void exitGame() {
        stage.close();
    }
}