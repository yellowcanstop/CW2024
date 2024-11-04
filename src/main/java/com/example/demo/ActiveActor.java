package com.example.demo;

import javafx.scene.image.*;
import com.example.demo.assets.*;

public abstract class ActiveActor extends ImageView {

	public final ImageAssetManager imageManager;

	public ActiveActor(String imageName, int imageHeight, double initialXPos, double initialYPos, ImageAssetManager imageManager) {
		this.imageManager = imageManager;
		this.setImage(imageManager.load(imageName));
		this.setLayoutX(initialXPos);
		this.setLayoutY(initialYPos);
		this.setFitHeight(imageHeight);
		this.setPreserveRatio(true);
	}

	public abstract void updatePosition();

	protected void moveHorizontally(double horizontalMove) {
		this.setTranslateX(getTranslateX() + horizontalMove);
	}

	protected void moveVertically(double verticalMove) {
		this.setTranslateY(getTranslateY() + verticalMove);
	}

}
