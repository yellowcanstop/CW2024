package com.example.demo.views.components;

import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import com.example.demo.assets.*;

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
	 * @param imageManager - the image asset manager to load the image
	 * @param soundManager - the sound asset manager to load the sound
	 */
	public GameOverImage(double xPosition, double yPosition, ImageAssetManager imageManager, SoundAssetManager soundManager) {
		this.setImage(imageManager.load(AssetPaths.GAMEOVER));
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
		this.sound = soundManager.load(AssetPaths.GAMEOVER_SOUND);
	}

	/**
	 * Play the sound when the game is over.
	 */
	public void playSound() {
		if (sound != null) {
			// loop continuously until stopped for gameover music
			sound.setCycleCount(AudioClip.INDEFINITE);
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
