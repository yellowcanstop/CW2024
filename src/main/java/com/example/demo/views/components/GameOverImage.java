package com.example.demo.views.components;

import com.example.demo.utils.AssetPaths;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import java.util.Objects;

/**
 * View displaying image and playing sound when the game is over.
 */
public class GameOverImage extends ImageView {
	private final AudioClip sound;

	/**
	 * Constructor to create an instance of GameOverImage.
	 *
	 * @param xPosition - the x position of the image
	 * @param yPosition - the y position of the image
	 */
	public GameOverImage(double xPosition, double yPosition) {
		this.setImage(new Image(Objects.requireNonNull(getClass().getResource(AssetPaths.GAMEOVER)).toExternalForm()));
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
		this.sound = new AudioClip(Objects.requireNonNull(getClass().getResource(AssetPaths.GAMEOVER_SOUND)).toExternalForm());
	}

	/**
	 * Play the sound when the game is over.
	 */
	public void playSound() {
		if (sound != null) {
			// loop continuously until stopped for gameover music
			// sound.setCycleCount(AudioClip.INDEFINITE);
			sound.play();
		}
	}

	/**
	 * Stop the sound when navigating away from the game over screen.
	 */
	public void stopSound() {
		if (sound.isPlaying()) {
			sound.stop();
		}
	}
}
