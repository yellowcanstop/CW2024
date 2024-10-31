package com.example.demo.controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import com.example.demo.LevelParent;

public class Controller implements Observer {

	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.LevelOne";
	private final Stage stage;

	public Controller(Stage stage) {
		this.stage = stage;
	}

	public void launchGame() {
		try {
			stage.show();
			goToLevel(LEVEL_ONE_CLASS_NAME);
		} catch (Exception e) {
			AlertUtils.alertException(e);
		}
	}

	private void goToLevel(String className) {
		try {	
			Class<?> myClass = Class.forName(className);
			Constructor<?> constructor = myClass.getConstructor(double.class, double.class);
			LevelParent myLevel = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth());
			myLevel.addObserver(this);
			Scene scene = myLevel.initializeScene();
			stage.setScene(scene);
			myLevel.startGame();
		} catch (Exception e) {
			AlertUtils.alertException(e);
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// Check type safety for a safe cast
		if (arg1 instanceof String) {
            try {
                goToLevel((String) arg1);
            } catch (Exception e) {
                AlertUtils.alertException(e);
            }
        } else {
            AlertUtils.alertException(new IllegalArgumentException("Specified level name is not an instance of String."));
        }
	}

}
