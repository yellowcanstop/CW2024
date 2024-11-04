package com.example.demo;

import javafx.scene.Group;
import com.example.demo.assets.*;

public class LevelView {
	
	private static final double HEART_DISPLAY_X_POSITION = 5;
	private static final double HEART_DISPLAY_Y_POSITION = 25;
	private static final int WIN_IMAGE_X_POSITION = 355;
	private static final int WIN_IMAGE_Y_POSITION = 175;
	private static final int LOSS_SCREEN_X_POSITION = -160;
	private static final int LOSS_SCREEN_Y_POSISITION = -375;
	private final Group root;
	private final WinImage winImage;
	private final GameOverImage gameOverImage;
	private final HeartDisplay heartDisplay;
	private final ImageAssetManager imageManager;
	private final SoundAssetManager soundManager;
	
	public LevelView(Group root, int heartsToDisplay, ImageAssetManager imageManager, SoundAssetManager soundManager) {
		this.root = root;
		this.imageManager = imageManager;
		this.soundManager = soundManager;
		this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay, imageManager);
		this.winImage = new WinImage(WIN_IMAGE_X_POSITION, WIN_IMAGE_Y_POSITION, imageManager, soundManager);
		this.gameOverImage = new GameOverImage(LOSS_SCREEN_X_POSITION, LOSS_SCREEN_Y_POSISITION, imageManager, soundManager);
	}
	
	public void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
	}

	public void showWinImage() {
		root.getChildren().add(winImage);
		winImage.showWinImage();
	}
	
	public void showGameOverImage() {
		root.getChildren().add(gameOverImage);
	}
	
	public void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			heartDisplay.removeHeart();
		}
	}

	public void unloadResources() {
		heartDisplay.unloadResources();

		gameOverImage.stopSound();
		soundManager.unload(AssetPaths.GAMEOVER_SOUND);
		imageManager.unload(AssetPaths.GAMEOVER);

		winImage.stopSound();
		soundManager.unload(AssetPaths.WIN_SOUND);
		imageManager.unload(AssetPaths.YOU_WIN);
	}

}
