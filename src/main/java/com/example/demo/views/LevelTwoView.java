package com.example.demo.views;

import com.example.demo.views.components.ShieldImage;
import javafx.scene.Group;
import com.example.demo.assets.*;

/**
 * View for level two of the game.
 */
public class LevelTwoView extends LevelView {

	private static final int SHIELD_X_POSITION = 1150;
	private static final int SHIELD_Y_POSITION = 500;
	private final Group root;
	private final ShieldImage shieldImage;
	private final ImageAssetManager imageManager;
	private final SoundAssetManager soundManager;

	/**
	 * Constructor to create an instance of the view for Level Two.
	 *
	 * @param root - group of all nodes to be displayed in the scene
	 * @param heartsToDisplay - the initial number of hearts to be displayed
	 * @param imageManager - the image manager to load images
	 * @param soundManager - the sound manager to load sounds
	 */
	public LevelTwoView(Group root, int heartsToDisplay, ImageAssetManager imageManager, SoundAssetManager soundManager) {
		super(root, heartsToDisplay, imageManager, soundManager);
		this.root = root;
		this.imageManager = imageManager;
		this.soundManager = soundManager;
		this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION, imageManager);
		addImagesToRoot();
	}

	/**
	 * Add images to the root which is the group of all nodes to be displayed in the scene.
	 */
	private void addImagesToRoot() {
		root.getChildren().addAll(shieldImage);
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

	/**
	 * Unload resources when the level is no longer needed.
	 */
	@Override
	public void unloadResources() {
		super.unloadResources();
		imageManager.unload(AssetPaths.SHIELD);
	}
}
