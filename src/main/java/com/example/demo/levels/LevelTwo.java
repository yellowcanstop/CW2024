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
	private LevelTwoView levelView;

	/**
	 * Constructor to create an instance of LevelTwo. Initialize the bossPlane.
	 *
	 * @param screenHeight - the height of the screen
	 * @param screenWidth - the width of the screen
	 */
	public LevelTwo(double screenHeight, double screenWidth) {
		super(AssetPaths.BACKGROUND2, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		bossPlane = new BossPlane();
		this.levelView = new LevelTwoView(getRoot(), PLAYER_INITIAL_HEALTH);

		levelView.showShield();
	}

	/**
	 * Initialize the friendly units.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
		getRoot().getChildren().add(levelView.getShieldImage());
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
	protected LevelTwoView instantiateLevelView() {
		return levelView;
	}

	@Override
	protected void updateLevelView() {
		super.updateLevelView();
		checkIfBossShielded();
	}

	private void checkIfBossShielded() {
		if (bossPlane.isShielded()) {
			levelView.showShield();
		} else {
			levelView.showShield();
		}
	}

}
