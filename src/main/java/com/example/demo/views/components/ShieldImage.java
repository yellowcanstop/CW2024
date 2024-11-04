package com.example.demo.views.components;

import javafx.scene.image.ImageView;
import com.example.demo.assets.*;

/**
 * View component displaying an image of a shield which can be shown or hidden.
 */
public class ShieldImage extends ImageView {
	
	private static final int SHIELD_SIZE = 200;

	/**
	 * Constructor to create an instance of a ShieldImage.
	 *
	 * @param xPosition - initial x position of the shield image
	 * @param yPosition - initial y position of the shield image
	 * @param imageManager - image asset manager to load the shield image
	 */
	public ShieldImage(double xPosition, double yPosition, ImageAssetManager imageManager) {
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
		this.setImage(imageManager.load(AssetPaths.SHIELD));
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
