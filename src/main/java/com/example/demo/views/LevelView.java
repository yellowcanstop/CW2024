package com.example.demo.views;

import com.example.demo.Main;
import com.example.demo.views.components.GameOverImage;
import com.example.demo.views.components.HeartDisplay;
import com.example.demo.views.components.WinImage;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * View for a level of the game.
 */
public abstract class LevelView {
	private final Group root;
	private final WinImage winImage;
	private static final int WIN_IMAGE_X_POSITION = 355;
	private static final int WIN_IMAGE_Y_POSITION = 175;
	private final GameOverImage gameOverImage;
	private static final int LOSS_IMAGE_X_POSITION = -160;
	private static final int LOSS_IMAGE_Y_POSITION = -375;
	private final HeartDisplay heartDisplay;
	private static final int HEART_DISPLAY_X_POSITION = 5;
	private static final int HEART_DISPLAY_Y_POSITION = 25;
	private final VBox buttonContainer;
	private final Button returnButton;
	private static final int RETURN_BUTTON_X_POSITION = 400;
	private static final int RETURN_BUTTON_Y_POSITION = 200;

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
		this.gameOverImage = new GameOverImage(LOSS_IMAGE_X_POSITION, LOSS_IMAGE_Y_POSITION);
		this.returnButton = new Button("Return to Menu");
		this.returnButton.setOnAction(event -> Main.returnToMainScreen());
		this.buttonContainer = new VBox(returnButton);
		buttonContainer.setLayoutX(RETURN_BUTTON_X_POSITION);
		buttonContainer.setLayoutY(RETURN_BUTTON_Y_POSITION);
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
		showReturnButton();
	}

	/**
	 * Show the image when the game is over.
	 */
	public void showGameOverImage() {
		root.getChildren().add(gameOverImage);
		gameOverImage.showGameOverImage();
		gameOverImage.playSound();
		showReturnButton();
	}

	/**
	 * Show the button to return to menu.
	 */
	private void showReturnButton() {
		root.getChildren().add(buttonContainer);
	}

	/**
	 * Remove hearts from the heart display.
	 *
	 * @param heartsRemaining - the current number of hearts to be displayed
	 */
	public void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		int heartsToRemove = currentNumberOfHearts - heartsRemaining;
		for (int i = 0; i < heartsToRemove; i++) {
			heartDisplay.removeHeart();
		}
	}
}