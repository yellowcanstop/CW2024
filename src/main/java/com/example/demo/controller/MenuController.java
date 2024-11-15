package com.example.demo.controller;

import com.example.demo.utils.MusicLoader;
import com.example.demo.utils.ScreenLoader;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controller for the menu screen.
 */
public class MenuController implements ScreenController {
    private Scene scene;
    private Stage stage;
    public Button button1;
    public Button button2;
    public Button button3;
    public static final String MENU_MUSIC = "/com/example/demo/sounds/rain.mp3";
    private MusicLoader musicLoader;
    private static final String HELP_SCREEN = "/com/example/demo/views/HelpScreen.fxml";
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
        this.helpController = ScreenLoader.loadScreen(stage, HELP_SCREEN);
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