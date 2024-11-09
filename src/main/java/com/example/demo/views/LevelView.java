package com.example.demo.views;

import com.example.demo.views.components.GameOverImage;
import com.example.demo.views.components.HeartDisplay;
import com.example.demo.views.components.WinImage;
import javafx.scene.Group;

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

	/**
	 * Constructor to create an instance of LevelView.
	 *
	 * @param root - group of all nodes to be displayed in the scene
	 * @param heartsToDisplay - the initial number of hearts to be displayed
	 */
	public LevelView(Group root, int heartsToDisplay) {
		this.root = root;
		this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
		this.winImage = new WinImage(WIN_IMAGE_X_POSITION, WIN_IMAGE_Y_POSITION);
		this.gameOverImage = new GameOverImage(LOSS_SCREEN_X_POSITION, LOSS_SCREEN_Y_POSITION);
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
	 * Unload the resources used by the level view at the end of the game.
	 */
	public void unloadResources() {
		heartDisplay.unloadResources();

	}
}