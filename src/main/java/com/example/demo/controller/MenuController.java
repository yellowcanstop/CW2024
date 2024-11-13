package com.example.demo.controller;

import com.example.demo.Main;
import com.example.demo.utils.AssetPaths;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MenuController {
    public Button button1;
    public Button button2;
    public Button button3;
    private Stage stage;
    private Media media;
    private MediaPlayer mediaPlayer;
    private static HelpController helpController;
    private static Scene helpScene;
    private static final int SCREEN_WIDTH = 1300;
    private static final int SCREEN_HEIGHT = 750;


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

    @FXML
    private void showHelpScreen() throws IOException {
        // initialize help screen
        FXMLLoader loader = new FXMLLoader(MenuController.class.getResource("/com/example/demo/views/HelpScreen.fxml"));
        helpScene = new Scene(loader.load());
        helpController = loader.getController();
        helpController.setStage(stage);
        stage.setResizable(false);
        stage.setHeight(SCREEN_HEIGHT);
        stage.setWidth(SCREEN_WIDTH);
        // show help screen
        stage.setScene(helpScene);
        stage.show();
    }
}