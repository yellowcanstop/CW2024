package com.example.demo.levels;

import com.example.demo.models.BossPlane;
import com.example.demo.utils.AssetPaths;
import com.example.demo.views.LevelTwoView;
import com.example.demo.views.LevelView;

/**
 * Level two of the game.
 */
public class LevelTwo extends LevelParent {
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private final BossPlane bossPlane;

	/**
	 * Constructor to create an instance of LevelTwo. Initialize the bossPlane.
	 *
	 * @param screenHeight - the height of the screen
	 * @param screenWidth - the width of the screen
	 */
	public LevelTwo(double screenHeight, double screenWidth) {
		super(AssetPaths.BACKGROUND2, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		bossPlane = new BossPlane();
	}

	/**
	 * Initialize the friendly units.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	/**
	 * Check if user is destroyed or has destroyed the bossPlane to win the game.
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			this.unloadResources();
			loseGame();
		}
		else if (bossPlane.isDestroyed()) {
			this.unloadResources();
			winGame();
		}
	}

	/**
	 * Spawn the bossPlane.
	 */
	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(bossPlane);
		}
	}

	/**
	 * Instantiate the level view with all nodes and the player's initial health.
	 *
	 * @return the level view
	 */
	@Override
	protected LevelView instantiateLevelView() {
		return new LevelTwoView(getRoot(), PLAYER_INITIAL_HEALTH);
	}
}
