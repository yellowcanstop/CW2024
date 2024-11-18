package com.example.demo.controller;

import com.example.demo.utils.MusicLoader;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Controller for the menu screen.
 */
public class MenuController implements ScreenController {
    public Label titleLabel;
    private Scene scene;
    private Stage stage;
    public Button gameButton;
    public Button helpButton;
    public Button exitButton;
    public static final String MENU_MUSIC = "/com/example/demo/sounds/princess.wav";
    private MusicLoader musicLoader;
    private ScreenController helpController;

    /**
     * Initialize the scene, stage and media.
     *
     * @param scene - the scene to display the application
     * @param stage - the stage to display the application
     */
    @Override
    public void initialize(Scene scene, Stage stage) throws Exception {
        this.scene = scene;
        this.stage = stage;
        this.musicLoader = new MusicLoader(MENU_MUSIC);
        this.helpController = ScreenFactory.createHelpScreen(stage);
    }

    /**
     * Show the menu screen with music playing.
     */
    @Override
    public void showScreen() {
        stage.setScene(scene);
        stage.show();
        musicLoader.playMusic();
    }

    /**
     * Start the game.
     */
    @FXML
    private void startGame() {
        musicLoader.stopMusic();
        GameController myGameController = new GameController(stage);
        myGameController.launchGame();
    }

    /**
     * Exit the game.
     */
    @FXML
    private void exitGame() {
        musicLoader.stopMusic();
        stage.close();
    }

    /**
     * Show the help screen.
     */
    @FXML
    private void showHelpScreen() {
        helpController.showScreen();
    }
}