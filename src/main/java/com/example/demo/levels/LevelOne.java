package com.example.demo.levels;

import com.example.demo.models.DestructibleSprite;
import com.example.demo.models.EnemyPlane;
import com.example.demo.assets.*;
import com.example.demo.views.LevelView;

/**
 * Level one of the game.
 */
public class LevelOne extends LevelParent {
	
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";
	private static final String NEXT_LEVEL = "com.example.demo.levels.LevelTwo";
	private static final int TOTAL_ENEMIES = 5;
	private static final int KILLS_TO_ADVANCE = 10;
	private static final double ENEMY_SPAWN_PROBABILITY = .20;
	private static final int PLAYER_INITIAL_HEALTH = 5;

	/**
	 * Constructor to create an instance of LevelOne.
	 *
	 * @param screenHeight - the height of the screen
	 * @param screenWidth - the width of the screen
	 * @param imageManager - the image manager to be used for loading images
	 * @param soundManager - the sound manager to be used for loading sounds
	 */
	public LevelOne(double screenHeight, double screenWidth, ImageAssetManager imageManager, SoundAssetManager soundManager) {
		super(AssetPaths.BACKGROUND1, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH, imageManager, soundManager);
	}

	/**
	 * Check if user is destroyed or has reached the kill target to go to the next level.
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		}
		else if (userHasReachedKillTarget())
			goToNextLevel(NEXT_LEVEL);
	}

	/**
	 * Initialize the friendly units.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	/**
	 * Randomly spawn enemy units on a random y position on the screen.
	 */
	@Override
	protected void spawnEnemyUnits() {
		int currentNumberOfEnemies = getCurrentNumberOfEnemies();
		for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
			if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
				double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
				DestructibleSprite newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition, imageManager);
				addEnemyUnit(newEnemy);
			}
		}
	}

	/**
	 * Instantiate the level view with all nodes and the player's initial health.
	 *
	 * @return the level view
	 */
	@Override
	protected LevelView instantiateLevelView() {
		return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH, imageManager, soundManager);
	}

	/**
	 * Check if user has reached the target number of kills needed to advance to next level.
	 *
	 * @return true if kill target reached, else false
	 */
	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}

}
