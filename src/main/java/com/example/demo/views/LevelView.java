package com.example.demo.views;

import com.example.demo.views.components.GameOverImage;
import com.example.demo.views.components.HeartDisplay;
import com.example.demo.views.components.WinImage;
import javafx.scene.Group;
import com.example.demo.assets.*;

/**
 * View for a level of the game.
 */
public class LevelView {
	
	private static final double HEART_DISPLAY_X_POSITION = 5;
	private static final double HEART_DISPLAY_Y_POSITION = 25;
	private static final int WIN_IMAGE_X_POSITION = 355;
	private static final int WIN_IMAGE_Y_POSITION = 175;
	private static final int LOSS_SCREEN_X_POSITION = -160;
	private static final int LOSS_SCREEN_Y_POSITION = -375;
	private final Group root;
	private final WinImage winImage;
	private final GameOverImage gameOverImage;
	private final HeartDisplay heartDisplay;
	private final ImageAssetManager imageManager;
	private final SoundAssetManager soundManager;

	/**
	 * Constructor to create an instance of LevelView.
	 *
	 * @param root - group of all nodes to be displayed in the scene
	 * @param heartsToDisplay - the initial number of hearts to be displayed
	 * @param imageManager - the image manager to load images
	 * @param soundManager - the sound manager to load sounds
	 */
	public LevelView(Group root, int heartsToDisplay, ImageAssetManager imageManager, SoundAssetManager soundManager) {
		this.root = root;
		this.imageManager = imageManager;
		this.soundManager = soundManager;
		this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay, imageManager);
		this.winImage = new WinImage(WIN_IMAGE_X_POSITION, WIN_IMAGE_Y_POSITION, imageManager, soundManager);
		this.gameOverImage = new GameOverImage(LOSS_SCREEN_X_POSITION, LOSS_SCREEN_Y_POSITION, imageManager, soundManager);
	}

	/**
	 * Show a container of hearts representing the remaining lives of the player.
	 */
	public void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
	}

	/**
	 * Show the image when the game is won.
	 */
	public void showWinImage() {
		root.getChildren().add(winImage);
		winImage.showWinImage();
		winImage.playSound();
	}

	/**
	 * Show the image when the game is over.
	 */
	public void showGameOverImage() {
		root.getChildren().add(gameOverImage);
		gameOverImage.playSound();
	}

	/**
	 * Remove hearts from the heart display.
	 *
	 * @param heartsRemaining - the current number of hearts to be displayed
	 */
	public void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			heartDisplay.removeHeart();
		}
	}

	/**
	 * Unload the resources used by the level view.
	 */
	public void unloadResources() {
		heartDisplay.unloadResources();

		gameOverImage.stopSound();
		soundManager.unload(AssetPaths.GAMEOVER_SOUND);
		imageManager.unload(AssetPaths.GAMEOVER);

		winImage.stopSound();
		soundManager.unload(AssetPaths.WIN_SOUND);
		imageManager.unload(AssetPaths.YOU_WIN);
	}

}
