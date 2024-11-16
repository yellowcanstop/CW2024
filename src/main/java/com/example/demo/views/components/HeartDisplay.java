package com.example.demo.views.components;

import com.example.demo.utils.SoundLoader;
import javafx.scene.layout.HBox;

/**
 * View component displaying a container of hearts representing the remaining lives of the player.
 */
public class HeartDisplay {
	private static final int INDEX_OF_FIRST_ITEM = 0;
	private final HBox container;
	private final int numberOfHeartsToDisplay;
	private final SoundLoader soundLoader = new SoundLoader(HEART_LOSS);
	public static final String HEART_LOSS = "/com/example/demo/sounds/ugh.wav";

	/**
	 * Constructor to create an instance of HeartDisplay.
	 *
	 * @param xPosition - x position of the display
	 * @param yPosition - y position of the display
	 * @param heartsToDisplay - initial number of hearts to display
	 */
	public HeartDisplay(double xPosition, double yPosition, int heartsToDisplay) {
		this.container = new HBox();
		this.container.setLayoutX(xPosition);
		this.container.setLayoutY(yPosition);
		this.numberOfHeartsToDisplay = heartsToDisplay;
		initializeHearts();
	}

	/**
	 * Create each heart as a separate instance and add it to the container.
	 */
	private void initializeHearts() {
		for (int i = 0; i < numberOfHeartsToDisplay; i++) {
			Heart heart = new Heart();
			container.getChildren().add(heart);
		}
	}

	/**
	 * Remove a heart from the container.
	 */
	public void removeHeart() {
		if (!container.getChildren().isEmpty()) {
			container.getChildren().remove(INDEX_OF_FIRST_ITEM);
		}
	}

	/**
	 * Play the sound of a heart being lost.
	 */
	public void playSound() {
		soundLoader.playSound();
	}

	/**
	 * Get the container holding all the hearts representing the HeartDisplay.
	 *
	 * @return the container holding all the hearts
	 */
	public HBox getContainer() {
		return container;
	}

	/**
	 * Get the current number of hearts in the container.
	 *
	 * @return the current number of hearts
	 */
	public int getCurrentNumberOfHearts() {
		return container.getChildren().size();
	}
}
