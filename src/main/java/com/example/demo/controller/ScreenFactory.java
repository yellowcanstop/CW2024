package com.example.demo.controller;

import com.example.demo.utils.ScreenLoader;
import javafx.stage.Stage;

/**
 * Factory class to create screens.
 */
public class ScreenFactory {
    private static final String MENU_SCREEN = "/com/example/demo/views/MenuScreen.fxml";
    private static final String HELP_SCREEN = "/com/example/demo/views/HelpScreen.fxml";

    /**
     * Create the menu screen.
     *
     * @param stage - the stage to display the application
     * @return the menu screen
     * @throws Exception if the screen cannot be created
     */
    public static ScreenController createMenuScreen(Stage stage) throws Exception {
        return ScreenLoader.loadScreen(stage, MENU_SCREEN);
    }

    /**
     * Create the help screen.
     *
     * @param stage - the stage to display the application
     * @return the help screen
     * @throws Exception if the screen cannot be created
     */
    public static ScreenController createHelpScreen(Stage stage) throws Exception {
        return ScreenLoader.loadScreen(stage, HELP_SCREEN);
    }
}