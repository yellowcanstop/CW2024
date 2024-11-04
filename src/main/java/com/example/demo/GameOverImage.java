package com.example.demo;

import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import com.example.demo.assets.*;

public class GameOverImage extends ImageView {

	private AudioClip sound;

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
