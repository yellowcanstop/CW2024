package com.example.demo.controller;

import com.example.demo.utils.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

/**
 * Controller for the menu screen.
 */
public class MenuController implements ViewController {
    private Scene scene;
    private Stage stage;
    public Button button1;
    public Button button2;
    public Button button3;
    public static final String MENU_MUSIC = "/com/example/demo/sounds/rain.mp3";
    private MediaPlayer mediaPlayer;
    private static final String HELP_SCREEN = "/com/example/demo/views/HelpScreen.fxml";

    /**
     * Initialize the scene, stage and media.
     *
     * @param scene - the scene to display the application
     * @param stage - the stage to display the application
     */
    @Override
    public void initialize(Scene scene, Stage stage) {
        this.scene = scene;
        this.stage = stage;
        setMedia();
    }

    /**
     * Show the menu screen with music playing.
     */
    @Override
    public void showScreen() {
        stage.setScene(scene);
        stage.show();
        playMusic();
    }

    /**
     * Set the media for the menu screen.
     */
    private void setMedia() {
        Media media = new Media(Objects.requireNonNull(getClass().getResource(MENU_MUSIC)).toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    /**
     * Play the music for the menu screen.
     */
    private void playMusic() {
        mediaPlayer.play();
    }

    /**
     * Stop the music for the menu screen.
     */
    private void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    /**
     * Start the game.
     */
    @FXML
    private void startGame() {
        stopMusic();
        GameController myGameController = new GameController(stage);
        myGameController.launchGame();
    }

    /**
     * Exit the game.
     */
    @FXML
    private void exitGame() {
        stopMusic();
        stage.close();
    }

    /**
     * Show the help screen.
     *
     * @throws IOException if the FXML file is not found
     */
    @FXML
    private void showHelpScreen() throws IOException {
        ViewController helpController = ViewLoader.loadView(stage, HELP_SCREEN);
        helpController.showScreen();
    }
}