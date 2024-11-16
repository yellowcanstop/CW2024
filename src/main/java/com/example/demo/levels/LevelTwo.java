package com.example.demo.levels;

import com.example.demo.models.BossPlane;
import com.example.demo.views.LevelTwoView;

/**
 * Level two of the game.
 */
public class LevelTwo extends LevelParent {
	private final LevelTwoView levelView;
	private final BossPlane bossPlane;
	private static final String NEXT_LEVEL = "com.example.demo.levels.LevelThree";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	public static final String BACKGROUND2 = "/com/example/demo/images/background2.jpg";
	public static final String MUSIC2 = "/com/example/demo/sounds/rain.mp3";

	/**
	 * Constructor to create an instance of LevelTwo. Initialize the bossPlane.
	 *
	 * @param screenHeight - the height of the screen
	 * @param screenWidth - the width of the screen
	 */
	public LevelTwo(double screenHeight, double screenWidth) {
		super(BACKGROUND2, MUSIC2, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		bossPlane = new BossPlane();
		this.levelView = new LevelTwoView(getRoot(), PLAYER_INITIAL_HEALTH);
	}

	/**
	 * Initialize units for the level.
	 */
	@Override
	protected void initializeUnits() {
		super.initializeUnits();
		getRoot().getChildren().add(bossPlane.getShieldImage());
	}

	/**
	 * Initialize the level view.
	 */
	@Override
	protected void initializeLevelView() {
		super.initializeLevelView();
		getRoot().getChildren().add(levelView.getBossHealthLabel());
		getRoot().getChildren().add(levelView.getBossHealthDisplay());
	}

	/**
	 * Check if user is destroyed or has destroyed the bossPlane to win the game.
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		}
		else if (bossPlane.isDestroyed()) {
			goToNextLevel(NEXT_LEVEL);
		}
	}

	/**
	 * Spawn the boss plane.
	 */
	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(bossPlane);
		}
	}

	/**
	 * Get the level view.
	 *
	 * @return the level view
	 */
	@Override
	protected LevelTwoView getLevelView() {
		return levelView;
	}

	/**
	 * Update the level view and check if the boss is shielded.
	 */
	@Override
	protected void updateLevelView() {
		super.updateLevelView();
		checkIfBossShielded();
		levelView.updateBossHealth(bossPlane.getHealth());
	}

	/**
	 * Check if the boss is shielded and show the shield if it is.
	 */
	private void checkIfBossShielded() {
		if (bossPlane.isShielded()) {
			bossPlane.getShieldImage().showShield();
		} else {
			bossPlane.getShieldImage().hideShield();
		}
	}
}
