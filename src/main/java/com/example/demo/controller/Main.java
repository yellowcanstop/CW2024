package com.example.demo.controller;

import java.lang.reflect.InvocationTargetException;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	private static final int SCREEN_WIDTH = 1300;
	private static final int SCREEN_HEIGHT = 750;
	private static final String TITLE = "Sky Battle";
	private Controller myController;

	@Override
    public void start(Stage stage) {
        try {
            stage.setTitle(TITLE);
            stage.setResizable(false);
            stage.setHeight(SCREEN_HEIGHT);
            stage.setWidth(SCREEN_WIDTH);
            myController = new Controller(stage);
            myController.launchGame();
        } catch (Exception e) {
            AlertUtils.alertException(e);
        }
    }

	public static void main(String[] args) {
		launch();
	}
}