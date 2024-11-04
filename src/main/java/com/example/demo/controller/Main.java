package com.example.demo.controller;

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
            AlertUtils.alertException(e);
        }
    }

    /*
	@Override
	public void stop() {
		// TODO - unload assets used across multiple levels or scenes e.g. sound assets
		soundManager.unload("test.wav");
	}
	*/

    /**
     * Main method to launch the game application.
     *
     * @param args - command line arguments
     */
	public static void main(String[] args) {
		launch();
	}
}