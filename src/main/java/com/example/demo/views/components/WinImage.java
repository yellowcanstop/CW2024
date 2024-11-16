package com.example.demo.views.components;

import com.example.demo.utils.SoundLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Objects;

/**
 * View displaying image and playing sound when the game is won.
 */
public class WinImage extends ImageView {
	private final SoundLoader soundLoader;
	private static final int HEIGHT = 500;
	private static final int WIDTH = 600;
	public static final String YOU_WIN = "/com/example/demo/images/youwin.png";
	public static final String WIN_SOUND = "/com/example/demo/sounds/boodoodaloop.wav";

	/**
	 * Constructor to create an instance of WinImage.
	 *
	 * @param xPosition - the x position of the image
	 * @param yPosition - the y position of the image
	 */
	public WinImage(double xPosition, double yPosition) {
		this.setImage(new Image(Objects.requireNonNull(getClass().getResource(YOU_WIN)).toExternalForm()));
		this.setVisible(false);
		this.setFitHeight(HEIGHT);
		this.setFitWidth(WIDTH);
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
		this.soundLoader = new SoundLoader(WIN_SOUND);
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
		soundLoader.playSound();
	}
}