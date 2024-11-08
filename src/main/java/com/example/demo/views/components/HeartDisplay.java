package com.example.demo.views.components;

import com.example.demo.views.components.Heart;
import javafx.scene.layout.HBox;
import com.example.demo.assets.*;

/**
 * View component displaying a container of hearts representing the remaining lives of the player.
 */
public class HeartDisplay {
	private static final int INDEX_OF_FIRST_ITEM = 0;
	private final HBox container;
	private int numberOfHeartsToDisplay;
	private final ImageAssetManager imageManager;

	/**
	 * Constructor to create an instance of HeartDisplay.
	 *
	 * @param xPosition - x position of the display
	 * @param yPosition - y position of the display
	 * @param heartsToDisplay - initial number of hearts to display
	 * @param imageManager - ImageAssetManager to load the heart image
	 */
	public HeartDisplay(double xPosition, double yPosition, int heartsToDisplay, ImageAssetManager imageManager) {
		this.imageManager = imageManager;
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
			Heart heart = new Heart(imageManager);
			container.getChildren().add(heart);
		}
	}

	/**
	 * Remove a heart from the container.
	 */
	public void removeHeart() {
		if (!container.getChildren().isEmpty())
			container.getChildren().remove(INDEX_OF_FIRST_ITEM);
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
	 * Unload resources when the heart display is no longer needed
	 */
	public void unloadResources() {
		container.getChildren().clear();
		imageManager.unload(AssetPaths.HEART);
	}
}