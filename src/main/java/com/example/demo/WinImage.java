package com.example.demo;

import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import com.example.demo.assets.*;

public class WinImage extends ImageView {
	
	private static final int HEIGHT = 500;
	private static final int WIDTH = 600;
	private AudioClip sound;
	
	public WinImage(double xPosition, double yPosition, ImageAssetManager imageManager, SoundAssetManager soundManager) {
		this.setImage(imageManager.load(AssetPaths.YOU_WIN));
		this.setVisible(false);
		this.setFitHeight(HEIGHT);
		this.setFitWidth(WIDTH);
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
		this.sound = soundManager.load(AssetPaths.WIN_SOUND);
	}
	
	public void showWinImage() {
		this.setVisible(true);
	}

	/**
	 * Play the sound when the game is won.
	 */
	public void playSound() {
		if (sound != null) {
			sound.play();
		}
	}

	/**
	 * Stop the sound when navigating away from the win screen.
	 */
	public void stopSound() {
		if (sound.isPlaying()) {
			sound.stop();
		}
	}
}
