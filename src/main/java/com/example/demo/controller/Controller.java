package com.example.demo.controller;

import java.lang.reflect.Constructor;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.demo.LevelParent;
import com.example.demo.assets.*;

public class Controller implements Observer {

	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.LevelOne";
	private final Stage stage;
	private final ImageAssetManager imageManager;
	private final SoundAssetManager soundManager;
	private LevelParent myLevel;

	public Controller(Stage stage) {
		this.stage = stage;
		this.imageManager = new ImageAssetManager();
		this.soundManager = new SoundAssetManager();
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
			/*
			// Unload resources from the previous level before going to the next level
			if (myLevel != null) {
				myLevel.unloadResources();
			}

			 */
			Class<?> myClass = Class.forName(className);
			Constructor<?> constructor = myClass.getConstructor(double.class, double.class, ImageAssetManager.class, SoundAssetManager.class);
			myLevel = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth(), imageManager, soundManager);
			myLevel.addObserver(this);
			Scene scene = myLevel.initializeScene();
			stage.setScene(scene);
			myLevel.startGame();
		} catch (Exception e) {
			AlertUtils.alertException(e);
		}
	}

	@Override
	public void update(Observable observable, Object levelName) {
		// Check type safety for a safe cast
		if (levelName instanceof String) {
            try {
                goToLevel((String) levelName);
            } catch (Exception e) {
                AlertUtils.alertException(e);
            }
        } else {
            AlertUtils.alertException(new IllegalArgumentException("Specified level name is not an instance of String."));
        }
	}

}
