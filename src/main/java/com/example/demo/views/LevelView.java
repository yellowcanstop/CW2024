package com.example.demo.views;

import com.example.demo.Main;
import com.example.demo.views.components.GameOverImage;
import com.example.demo.views.components.HeartDisplay;
import com.example.demo.views.components.WinImage;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;

import java.util.Objects;

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
	private final Button returnButton;
	private final Label killCounterLabel;
	public static final String DAMAGE_USER = "/com/example/demo/sounds/ugh.wav";
	private static final AudioClip damageUserSound = new AudioClip(Objects.requireNonNull(LevelView.class.getResource(DAMAGE_USER)).toExternalForm());

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
		this.returnButton = new Button("Return to Menu");
		this.returnButton.setOnAction(event -> Main.returnToMenu());
		this.killCounterLabel = new Label("Kills: 0");
		this.killCounterLabel.setLayoutX(HEART_DISPLAY_X_POSITION);
		this.killCounterLabel.setLayoutY(HEART_DISPLAY_Y_POSITION + 50);
	}

	/**
	 * Show the kill counter.
	 */
	public void showKillCounter() {
		root.getChildren().add(killCounterLabel);
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
		VBox buttonContainer = new VBox(returnButton);
		buttonContainer.setLayoutX(WIN_IMAGE_X_POSITION + 50);
		buttonContainer.setLayoutY(WIN_IMAGE_Y_POSITION);
		root.getChildren().add(buttonContainer);
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
			damageUserSound.play();
		}
	}

	/**
	 * Update counter display to show the kills scored.
	 *
	 * @param killCount - the number of kills
	 */
	public void updateCounter(int killCount) {
		killCounterLabel.setText("Kills: " + killCount);
	}
}