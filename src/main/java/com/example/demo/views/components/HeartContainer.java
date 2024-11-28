package com.example.demo.views.components;

import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Objects;

/**
 * View component displaying a container of hearts representing the remaining lives of the player.
 */
public class HeartContainer {
	private static final int INDEX_OF_FIRST_ITEM = 0;
	private final HBox container;
	private final double heartHeight;
	private final int numberOfHeartsToDisplay;
	public static final String HEART = "/com/example/demo/images/heart.png";

	/**
	 * Constructor to create an instance of HeartContainer.
	 *
	 * @param xPosition - x position of the display
	 * @param yPosition - y position of the display
	 * @param heartsToDisplay - initial number of hearts to display
	 */
	public HeartContainer(double heartHeight, double xPosition, double yPosition, int heartsToDisplay) {
		this.heartHeight = heartHeight;
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
			ImageView heart = new ImageView();
			heart.setImage(new Image(Objects.requireNonNull(getClass().getResource(HEART)).toExternalForm()));
			heart.setFitHeight(heartHeight);
			heart.setPreserveRatio(true);
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
	 * Get the container holding all the hearts representing the HeartContainer.
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