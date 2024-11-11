package com.example.demo.controller;

import com.example.demo.utils.AssetPaths;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.util.Objects;

public class MenuController {
    public Button button1;
    public Button button2;
    private Stage stage;
    private Media media;
    private MediaPlayer mediaPlayer;

    public void setStage(Stage stage) {
        this.stage = stage;
        this.media = new Media(Objects.requireNonNull(getClass().getResource(AssetPaths.MENU_MUSIC)).toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    public void playMusic() {
        mediaPlayer.play();
    }

    private void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    @FXML
    private void startGame() {
        stopMusic();
        GameController myGameController = new GameController(stage);
        myGameController.launchGame();
    }

    @FXML
    private void exitGame() {
        stopMusic();
        stage.close();
    }
}