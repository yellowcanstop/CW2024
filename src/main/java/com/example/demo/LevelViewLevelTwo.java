package com.example.demo;

import javafx.scene.Group;
import com.example.demo.assets.*;

public class LevelViewLevelTwo extends LevelView {

	private static final int SHIELD_X_POSITION = 1150;
	private static final int SHIELD_Y_POSITION = 500;
	private final Group root;
	private final ShieldImage shieldImage;
	private final ImageAssetManager imageManager;
	private final SoundAssetManager soundManager;
	
	public LevelViewLevelTwo(Group root, int heartsToDisplay, ImageAssetManager imageManager, SoundAssetManager soundManager) {
		super(root, heartsToDisplay, imageManager, soundManager);
		this.root = root;
		this.imageManager = imageManager;
		this.soundManager = soundManager;
		this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION, imageManager);
		addImagesToRoot();
	}
	
	private void addImagesToRoot() {
		root.getChildren().addAll(shieldImage);
	}
	
	public void showShield() {
		shieldImage.showShield();
	}

	public void hideShield() {
		shieldImage.hideShield();
	}

	@Override
	public void unloadResources() {
		super.unloadResources();
		imageManager.unload(AssetPaths.SHIELD);
	}
}
