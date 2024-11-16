package com.example.demo.views.components;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Objects;

/**
 * View component displaying an image of a shield which can be shown or hidden.
 */
public class ShieldImage extends ImageView {
	private static final int SHIELD_SIZE = 200;
	public static final String SHIELD = "/com/example/demo/images/shield.png";

	/**
	 * Constructor to create an instance of a ShieldImage.
	 *
	 * @param xPosition - initial x position of the shield image
	 * @param yPosition - initial y position of the shield image
	 */
	public ShieldImage(double xPosition, double yPosition) {
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
		this.setImage(new Image(Objects.requireNonNull(getClass().getResource(SHIELD)).toExternalForm()));
		this.setVisible(false);
		this.setFitHeight(SHIELD_SIZE);
		this.setFitWidth(SHIELD_SIZE);
	}

	/**
	 * Show the image of a shield.
	 */
	public void showShield() {
		this.setVisible(true);
	}

	/**
	 * Hide a currently displayed image of a shield.
	 */
	public void hideShield() {
		this.setVisible(false);
	}
}