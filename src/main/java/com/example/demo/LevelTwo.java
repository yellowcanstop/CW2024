package com.example.demo;

import com.example.demo.assets.*;

public class LevelTwo extends LevelParent {

	private static final int PLAYER_INITIAL_HEALTH = 5;
	private final Boss boss;

	public LevelTwo(double screenHeight, double screenWidth, ImageAssetManager imageManager, SoundAssetManager soundManager) {
		super(AssetPaths.BACKGROUND2, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, imageManager, soundManager);
		boss = new Boss(imageManager);
	}

	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		}
		else if (boss.isDestroyed()) {
			winGame();
		}
	}

	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(boss);
		}
	}

	@Override
	protected LevelView instantiateLevelView() {
		return new LevelViewLevelTwo(getRoot(), PLAYER_INITIAL_HEALTH, imageManager, soundManager);
	}

}
