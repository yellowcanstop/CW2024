package com.example.demo.views;

import com.example.demo.views.components.ShieldImage;
import javafx.scene.Group;
import javafx.scene.image.ImageView;

/**
 * View for level two of the game.
 */
public class LevelTwoView extends LevelView {
	private static final int SHIELD_X_POSITION = 1150;
	private static final int SHIELD_Y_POSITION = 500;
	private final Group root;
	private final ShieldImage shieldImage;

	/**
	 * Constructor to create an instance of the view for Level Two.
	 *
	 * @param root - group of all nodes to be displayed in the scene
	 * @param heartsToDisplay - the initial number of hearts to be displayed
	 */
	public LevelTwoView(Group root, int heartsToDisplay) {
		super(root, heartsToDisplay);
		this.root = root;
		this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION);
		//addImagesToRoot();
	}

	/**
	 * Add images to the root which is the group of all nodes to be displayed in the scene.
	 */
	private void addImagesToRoot() {
		root.getChildren().addAll(shieldImage);
	}

	public ShieldImage getShieldImage() {
		return shieldImage;
	}

	/**
	 * Show the image of a shield.
	 */
	public void showShield() {
		shieldImage.showShield();
	}

	/**
	 * Hide a currently displayed image of a shield.
	 */
	public void hideShield() {
		shieldImage.hideShield();
	}
}