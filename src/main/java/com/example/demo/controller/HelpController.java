package com.example.demo.controller;

import com.example.demo.Main;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Controller for the help screen.
 */
public class HelpController implements ScreenController {
    public Button returnButton;
    public Label levelDescHeading;
    public Label levelDesc1;
    public Label levelDesc2;
    public Label levelDesc3;
    public Label keyActionsHeading;
    public Label keyActions1;
    public Label keyActions2;
    public Label keyActions3;
    public Label levelDesc4;
    private Stage stage;
    private Scene scene;

    /**
     * Initialize the scene and stage.
     *
     * @param scene - the scene to display the application
     * @param stage - the stage to display the application
     */
    @Override
    public void initialize(Scene scene, Stage stage) {
        this.scene = scene;
        this.stage = stage;
    }

    /**
     * Show the help screen.
     */
    @Override
    public void showScreen() {
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Return to the main menu screen.
     */
    @FXML
    private void returnToMenu() {
        Main.returnToMainScreen();
    }
}