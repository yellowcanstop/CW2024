package com.example.demo.controller;

import java.lang.reflect.Constructor;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.demo.utils.AlertException;
import com.example.demo.levels.LevelParent;
import com.example.demo.assets.*;

/**
 * Controls the logical flow of the game by creating instances of the levels and switching between them.
 * <p>
 * Implements the Observer design pattern: the Controller is an observer of the level's change in state.
 * The original code uses Observer for Controller and Observable for LevelParent from java.util, which are deprecated in Java 9.
 * This has been refactored to use InvalidationListener (instead of Observer) and Observable from javafx.beans.
 */
public class Controller implements InvalidationListener {

	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.levels.LevelOne";
	private final Stage stage;
	private final ImageAssetManager imageManager;
	private final SoundAssetManager soundManager;
	private LevelParent myLevel;

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
            myLevel = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth(), imageManager, soundManager);
			myLevel.levelNameProperty().addListener(this);
			Scene scene = myLevel.initializeScene();
			stage.setScene(scene);
			myLevel.startGame();
		} catch (Exception e) {
			AlertException.alertException(e);
		}
	}

	/**
	 * Called when the value of the observable (the levelNameProperty of the LevelParent) changes.
	 * <p>
	 * This notifies the Controller (InvalidationListener) that the level has changed and the Controller should go to the new level.
	 *
	 * @param observable - the observable which is the levelNameProperty of the LevelParent
	 */
	@Override
	public void invalidated(Observable observable) {
		try {
			/* to mimic deleteObserver() when java.util.Observer and Observable were used
			 * the InvalidationListener is removed from the levelNameProperty when the level changes to avoid memory leaks.
			 */
			observable.removeListener(this);
			String levelName = myLevel.levelNameProperty().get();
			goToLevel(levelName);
		} catch (Exception e) {
			AlertException.alertException(e);
		}
	}



	/*
	public void stopGame() {
		// check overlap with stop() in main. is this necessary?
		LevelParent.unloadResources();
	}
	 */

}
