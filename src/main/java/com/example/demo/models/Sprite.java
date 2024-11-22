package com.example.demo.models;

import javafx.scene.image.*;
import java.util.Objects;

/**
 * Abstract class representing a sprite which can move in the game.
 */
public abstract class Sprite extends ImageView {
	/**
	 * Constructor to create an instance of a Sprite.
	 *
	 * @param imageName - the String classpath of the image for the actor
	 * @param imageHeight - the height of the image for the actor
	 * @param initialXPos - the initial x coordinate position of the actor
	 * @param initialYPos - the initial y coordinate position of the actor
	 */
	public Sprite(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		this.setImage(new Image(Objects.requireNonNull(getClass().getResource(imageName)).toExternalForm()));
		this.setLayoutX(initialXPos);
		this.setLayoutY(initialYPos);
		this.setFitHeight(imageHeight);
		this.setPreserveRatio(true);
	}

	/**
	 * Update the position of the actor.
	 */
	protected abstract void updatePosition();

	/**
	 * Move the actor horizontally by the parameter amount.
	 *
	 * @param horizontalMove - the additional horizontal distance to move
	 */
	protected void moveHorizontally(double horizontalMove) {
		this.setTranslateX(getTranslateX() + horizontalMove);
	}

	/**
	 * Move the actor vertically by the parameter amount.
	 *
	 * @param verticalMove - the additional vertical distance to move
	 */
	protected void moveVertically(double verticalMove) {
		this.setTranslateY(getTranslateY() + verticalMove);
	}
}