package com.example.demo.views.components;

import com.example.demo.utils.SoundLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import java.util.Objects;

/**
 * View displaying image and playing sound when the game is over.
 */
public class GameOverImage extends ImageView {
	private SoundLoader soundLoader;
	public static final String GAMEOVER = "/com/example/demo/images/gameover.png";
	public static final String GAMEOVER_SOUND = "/com/example/demo/sounds/ugh.wav";

	/**
	 * Constructor to create an instance of GameOverImage.
	 *
	 * @param xPosition - the x position of the image
	 * @param yPosition - the y position of the image
	 */
	public GameOverImage(double xPosition, double yPosition) {
		this.setImage(new Image(Objects.requireNonNull(getClass().getResource(GAMEOVER)).toExternalForm()));
		this.setVisible(false);
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
		this.soundLoader = new SoundLoader(GAMEOVER_SOUND);
	}

	/**
	 * Display the image when the game is over.
	 */
	public void showGameOverImage() {
		this.setVisible(true);
	}

	/**
	 * Play the sound when the game is over.
	 */
	public void playSound() {
		soundLoader.playSound();
	}
}
