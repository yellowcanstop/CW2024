package com.example.demo;

import com.example.demo.controller.ScreenController;
import com.example.demo.utils.AlertException;
import com.example.demo.utils.ScreenLoader;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Entry point of the program where the game application is launched.
 */
public class Main extends Application {
    private static ScreenController screenController;
    private static final String MENU_SCREEN = "/com/example/demo/views/MenuScreen.fxml";

    /**
     * Main method to launch the game application.
     *
     * @param args - command line arguments
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Set the stage and display the screen.
     *
     * @param stage - the stage to display the application
     */
    @Override
    public void start(Stage stage) {
        try {
            screenController = ScreenLoader.loadScreen(stage, MENU_SCREEN);
            screenController.showScreen();
        } catch (Exception e) {
            AlertException.alertException(e);
        }
    }

    /**
     * Return to the main screen.
     */
    public static void returnToMainScreen() {
        screenController.showScreen();
    }
}