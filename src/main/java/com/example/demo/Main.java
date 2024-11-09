package com.example.demo;

import com.example.demo.controller.Controller;
import com.example.demo.utils.AlertException;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Entry point of the program where the game application is launched.
 */
public class Main extends Application {
	private static final int SCREEN_WIDTH = 1300;
	private static final int SCREEN_HEIGHT = 750;
	private static final String TITLE = "Sky Battle";

    /**
     * Set the {@code stage} and launch the game using the {@link Controller}.
     *
     * @param stage - the stage to display the game
     */
    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle(TITLE);
            stage.setResizable(false);
            stage.setHeight(SCREEN_HEIGHT);
            stage.setWidth(SCREEN_WIDTH);
            Controller myController = new Controller(stage);
            myController.launchGame();
        } catch (Exception e) {
            AlertException.alertException(e);
        }
    }

    /**
     * Main method to launch the game application.
     *
     * @param args - command line arguments
     */
	public static void main(String[] args) {
		launch();
	}
}