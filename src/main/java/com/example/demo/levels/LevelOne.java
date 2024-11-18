package com.example.demo.levels;

import com.example.demo.models.DestructibleSprite;
import com.example.demo.models.EnemyPlane;
import com.example.demo.views.LevelOneView;

/**
 * Level one of the game.
 */
public class LevelOne extends LevelParent {
	private final LevelOneView levelView;
	private static final String NEXT_LEVEL = "com.example.demo.levels.LevelTwo";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	public static final String BACKGROUND1 = "/com/example/demo/images/leveloneBG.png";
	public static final String MUSIC1 = "/com/example/demo/sounds/loading.wav";
	private static final int TOTAL_ENEMIES = 5;
	private static final int KILLS_TO_ADVANCE = 10;
	private static final double ENEMY_SPAWN_PROBABILITY = .20;

	/**
	 * Constructor to create an instance of LevelOne.
	 *
	 * @param screenHeight - the height of the screen
	 * @param screenWidth - the width of the screen
	 */
	public LevelOne(double screenHeight, double screenWidth) {
		super(BACKGROUND1, MUSIC1, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		this.levelView = new LevelOneView(getRoot(), PLAYER_INITIAL_HEALTH);
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
	 * Spawn the enemy units.
	 */
	@Override
	protected void spawnEnemyUnits() {
		int currentNumberOfEnemies = getCurrentNumberOfEnemies();
		int enemiesToSpawn = TOTAL_ENEMIES - currentNumberOfEnemies;
		for (int i = 0; i < enemiesToSpawn; i++) {
			if (shouldSpawnEnemy()) {
				DestructibleSprite newEnemy = createEnemy();
				addEnemyUnit(newEnemy);
			}
		}
	}

	/**
	 * Check if an enemy should be spawned based on the probability.
	 *
	 * @return true if an enemy should be spawned, else false
	 */
	private boolean shouldSpawnEnemy() {
		return Math.random() < ENEMY_SPAWN_PROBABILITY;
	}

	/**
	 * Create a new enemy at a random y position.
	 *
	 * @return the new enemy
	 */
	private DestructibleSprite createEnemy() {
		double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
		return new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
	}

	/**
	 * Get the level view.
	 *
	 * @return the level view
	 */
	@Override
	protected LevelOneView getLevelView() {
		return levelView;
	}

	/**
	 * Check if user has reached the target number of kills needed to advance to next level.
	 *
	 * @return true if kill target reached, else false
	 */
	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}

	/**
	 * Initialize the level view for the game level.
	 */
	@Override
	protected void initializeLevelView() {
		super.initializeLevelView();
		levelView.showKillCounter();
	}

	/**
	 * Update the level view by removing hearts from the display based on the player's health.
	 */
	@Override
	protected void updateLevelView() {
		super.updateLevelView();
		levelView.updateCounter(getUser().getNumberOfKills());
	}
}
