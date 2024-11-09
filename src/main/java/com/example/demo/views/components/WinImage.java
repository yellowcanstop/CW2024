package com.example.demo.views.components;

import com.example.demo.utils.AssetPaths;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;

import java.util.Objects;

/**
 * View displaying image and playing sound when the game is won.
 */
public class WinImage extends ImageView {
	private static final int HEIGHT = 500;
	private static final int WIDTH = 600;
	private final AudioClip sound;

	/**
	 * Constructor to create an instance of WinImage.
	 *
	 * @param xPosition - the x position of the image
	 * @param yPosition - the y position of the image
	 */
	public WinImage(double xPosition, double yPosition) {
		this.setImage(new Image(Objects.requireNonNull(getClass().getResource(AssetPaths.YOU_WIN)).toExternalForm()));
		this.setVisible(false);
		this.setFitHeight(HEIGHT);
		this.setFitWidth(WIDTH);
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
		this.sound = new AudioClip(Objects.requireNonNull(getClass().getResource(AssetPaths.WIN_SOUND)).toExternalForm());
	}

	/**
	 * Display the image when the game is won.
	 */
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
