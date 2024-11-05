package com.example.demo.controller;

import java.lang.reflect.Constructor;
import java.util.Observable;
import java.util.Observer;

import com.example.demo.utils.AlertException;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.demo.levels.LevelParent;
import com.example.demo.assets.*;

/**
 * Controls the logical flow of the game by creating instances of the levels and switching between them.
 * <p>
 * Implements the Observer design pattern: the Controller is an {@code Observer} of the levels ({@code Observable}).
 * <p>
 * When the level changes state, the Controller's update method is called and switches to the next level.
 */
public class Controller implements Observer {

	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.levels.LevelOne";
	private final Stage stage;
	private final ImageAssetManager imageManager;
	private final SoundAssetManager soundManager;

    /**
	 * Constructor to create an instance of Controller.
	 *
	 * @param stage - the stage to display the game
	 */
	public Controller(Stage stage) {
		this.stage = stage;
		this.imageManager = new ImageAssetManager();
		this.soundManager = new SoundAssetManager();
	}

	/**
	 * Launch the game by showing the {@code stage} and going to the first level.
	 */
	public void launchGame() {
		try {
			stage.show();
			goToLevel(LEVEL_ONE_CLASS_NAME);
		} catch (Exception e) {
			AlertException.alertException(e);
		}
	}

	/**
	 * Go to the level specified by its parameter by using reflection to create an instance of the level class.
	 *
	 * @param className - the name of the level class to go to
	 */
	private void goToLevel(String className) {
		try {
			Class<?> myClass = Class.forName(className);
			Constructor<?> constructor = myClass.getConstructor(double.class, double.class, ImageAssetManager.class, SoundAssetManager.class);
            LevelParent myLevel = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth(), imageManager, soundManager);
			myLevel.addObserver(this);
			Scene scene = myLevel.initializeScene();
			stage.setScene(scene);
			myLevel.startGame();
		} catch (Exception e) {
			AlertException.alertException(e);
		}
	}

	/**
	 * Notify the Controller ({@code Observer}) that the level ({@code Observable}) has changed state, prompting a switch to the next level.
	 *
	 * @param observable - the observable object, required in the method signature by the Observer interface
	 * @param levelName - the name of the level class to go to
	 */
	@Override
	public void update(Observable observable, Object levelName) {
		// Check type safety for a safe cast
		if (levelName instanceof String) {
            try {
				observable.deleteObserver(this);
                goToLevel((String) levelName);
            } catch (Exception e) {
                AlertException.alertException(e);
            }
        } else {
            AlertException.alertException(new IllegalArgumentException("Specified level name is not an instance of String."));
        }
	}

	/*
	public void stopGame() {
		// check overlap with stop() in main. is this necessary?
		LevelParent.unloadResources();
	}
	 */

}
