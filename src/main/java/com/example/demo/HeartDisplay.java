package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import com.example.demo.assets.*;

public class HeartDisplay {
	private static final int INDEX_OF_FIRST_ITEM = 0;
	private final HBox container;
	private int numberOfHeartsToDisplay;
	private final ImageAssetManager imageManager;
	
	public HeartDisplay(double xPosition, double yPosition, int heartsToDisplay, ImageAssetManager imageManager) {
		this.imageManager = imageManager;
		this.container = new HBox();
		this.container.setLayoutX(xPosition);
		this.container.setLayoutY(yPosition);
		this.numberOfHeartsToDisplay = heartsToDisplay;
		initializeHearts();
	}

	private void initializeHearts() {
		for (int i = 0; i < numberOfHeartsToDisplay; i++) {
			Heart heart = new Heart(imageManager);
			container.getChildren().add(heart);
		}
	}
	
	public void removeHeart() {
		if (!container.getChildren().isEmpty())
			container.getChildren().remove(INDEX_OF_FIRST_ITEM);
	}
	
	public HBox getContainer() {
		return container;
	}

	public void unloadResources() {
		container.getChildren().clear();
		imageManager.unload(AssetPaths.HEART);
	}
}
