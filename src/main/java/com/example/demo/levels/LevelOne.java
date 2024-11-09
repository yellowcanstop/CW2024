package com.example.demo.levels;

import com.example.demo.models.DestructibleSprite;
import com.example.demo.models.EnemyPlane;
import com.example.demo.utils.AssetPaths;
import com.example.demo.views.LevelOneView;

/**
 * Level one of the game.
 */
public class LevelOne extends LevelParent {
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
	 */
	public LevelOne(double screenHeight, double screenWidth) {
		super(AssetPaths.BACKGROUND1, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
	}

	/**
	 * Check if user is destroyed or has reached the kill target to go to the next level.
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		}
		else if (userHasReachedKillTarget()) {
			goToNextLevel(NEXT_LEVEL);
		}
	}

	/**
	 * Initialize the friendly units.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	/**
	 * Randomly spawn enemy units at a random y position on the screen.
	 */
	@Override
	protected void spawnEnemyUnits() {
		int currentNumberOfEnemies = getCurrentNumberOfEnemies();
		for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
			if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
				double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
				DestructibleSprite newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
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
	protected LevelOneView instantiateLevelView() {
		return new LevelOneView(getRoot(), PLAYER_INITIAL_HEALTH);
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
