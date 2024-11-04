package com.example.demo;

import com.example.demo.assets.*;

/**
 * Level two of the game.
 */
public class LevelTwo extends LevelParent {

	private static final int PLAYER_INITIAL_HEALTH = 5;
	private final Boss boss;

	/**
	 * Constructor to create an instance of LevelTwo. Initialize the boss.
	 *
	 * @param screenHeight - the height of the screen
	 * @param screenWidth - the width of the screen
	 * @param imageManager - the image manager to be used for loading images
	 * @param soundManager - the sound manager to be used for loading sounds
	 */
	public LevelTwo(double screenHeight, double screenWidth, ImageAssetManager imageManager, SoundAssetManager soundManager) {
		super(AssetPaths.BACKGROUND2, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, imageManager, soundManager);
		boss = new Boss(imageManager);
	}

	/**
	 * Initialize the friendly units.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	/**
	 * Check if user is destroyed or has destroyed the boss to win the game.
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		}
		else if (boss.isDestroyed()) {
			winGame();
		}
	}

	/**
	 * Spawn the boss.
	 */
	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(boss);
		}
	}

	/**
	 * Instantiate the level view with all nodes and the player's initial health.
	 *
	 * @return the level view
	 */
	@Override
	protected LevelView instantiateLevelView() {
		return new LevelViewLevelTwo(getRoot(), PLAYER_INITIAL_HEALTH, imageManager, soundManager);
	}

}
