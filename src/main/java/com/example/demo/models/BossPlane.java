package com.example.demo.models;

import java.util.*;

import com.example.demo.utils.SoundLoader;
import com.example.demo.views.components.Shield;

/**
 * BossPlane sprite for level two of the game.
 */
public class BossPlane extends Plane {
	private static final double INITIAL_X_POSITION = 1000.0;
	private static final double INITIAL_Y_POSITION = 400;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;
	private static final double BOSS_FIRE_RATE = .04;
	private static final double BOSS_SHIELD_PROBABILITY = .02;
	private static final int IMAGE_HEIGHT = 300;
	private static final int VERTICAL_VELOCITY = 8;
	private static final int HEALTH = 10;
	private static final int MOVE_FREQUENCY_PER_CYCLE = 5;
	private static final int ZERO = 0;
	private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;
	private static final int Y_POSITION_UPPER_BOUND = -100;
	private static final int Y_POSITION_LOWER_BOUND = 475;
	private static final int MAX_FRAMES_WITH_SHIELD = 50;
	private final List<Integer> movePattern;
	private boolean isShielded;
	private int consecutiveMovesInSameDirection;
	private int indexOfCurrentMove;
	private int framesWithShieldActivated;
	private Shield shield;
	public static final String DESTROY_ENEMY = "/com/example/demo/sounds/gameover.wav";
	private SoundLoader soundLoader = new SoundLoader(DESTROY_ENEMY);
	public static final String BOSS_PLANE = "/com/example/demo/images/bossplane.png";
	private int maxHealth;

	/**
	 * Constructor to create an instance of a BossPlane.
	 */
	public BossPlane() {
		super(BOSS_PLANE, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
		movePattern = new ArrayList<>();
		consecutiveMovesInSameDirection = 0;
		indexOfCurrentMove = 0;
		framesWithShieldActivated = 0;
		maxHealth = HEALTH;
		isShielded = false;
		initializeMovePattern();
		shield = new Shield(INITIAL_X_POSITION, INITIAL_Y_POSITION);
		shield.translateXProperty().bind(this.translateXProperty());
		shield.translateYProperty().bind(this.translateYProperty());
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	/**
	 * Update position of the boss.
	 */
	@Override
	public void updatePosition() {
		double initialTranslateY = getTranslateY();
		moveVertically(getNextMove());
		double currentPosition = getLayoutY() + getTranslateY();
		if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
			setTranslateY(initialTranslateY);
		}
	}

	/**
	 * Update the boss by updating its position and the shield.
	 */
	@Override
	public void updateActor() {
		updatePosition();
		updateShield();
		fireBullet();
	}

	/**
	 * Fire a projectile from the boss.
	 *
	 * @return the projectile fired by the boss if the boss fires in the current frame, else null
	 */
	@Override
	public DestructibleSprite fireBullet() {
		if (Math.random() < BOSS_FIRE_RATE) {
			return BossBullet.create(getProjectileInitialPosition());
		}
		return null;
	}

	/**
	 * Take damage if the boss is not shielded.
	 */
	@Override
	public void takeDamage() {
		if (!isShielded) {
			super.takeDamage();
			soundLoader.playSound();
		}
	}

	/**
	 * Show or hide shield image based on whether the boss is shielded.
	 */
	public void toggleShieldVisibility() {
		if (isShielded) {
			shield.showShield();
		} else {
			shield.hideShield();
		}
	}

	/**
	 * Initialize the random movement pattern for the boss.
	 */
	private void initializeMovePattern() {
		for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
			movePattern.add(VERTICAL_VELOCITY);
			movePattern.add(-VERTICAL_VELOCITY);
			movePattern.add(ZERO);
		}
		Collections.shuffle(movePattern);
	}

	/**
	 * Get the shield image for the boss.
	 *
	 * @return the shield image for the boss
	 */
	public Shield getShieldImage() {
		return shield;
	}

	/**
	 * Update the shield based on shield activation status and duration.
	 */
	private void updateShield() {
		if (isShielded) framesWithShieldActivated++;
		else if (shieldShouldBeActivated()) activateShield();	
		if (shieldExhausted()) deactivateShield();
	}

	/**
	 * Get the next move for the boss.
	 *
	 * @return the integer value of the next move
	 */
	private int getNextMove() {
		int currentMove = movePattern.get(indexOfCurrentMove);
		consecutiveMovesInSameDirection++;
		if (consecutiveMovesInSameDirection == MAX_FRAMES_WITH_SAME_MOVE) {
			Collections.shuffle(movePattern);
			consecutiveMovesInSameDirection = 0;
			indexOfCurrentMove++;
		}
		if (indexOfCurrentMove == movePattern.size()) {
			indexOfCurrentMove = 0;
		}
		return currentMove;
	}

	/**
	 * Get the initial Y coordinate position for the projectile fired by the boss.
	 *
	 * @return the initial Y coordinate position for the projectile fired by the boss
	 */
	private double getProjectileInitialPosition() {
		return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
	}

	/**
	 * Check if the shield should be activated.
	 *
	 * @return true if the shield should be activated, else false
	 */
	private boolean shieldShouldBeActivated() {
		return Math.random() < BOSS_SHIELD_PROBABILITY;
	}

	/**
	 * Check if the shield has been activated for the allowed maximum number of frames.
	 *
	 * @return true if the shield has been activated for the allowed maximum number of frames, else false
	 */
	private boolean shieldExhausted() {
		return framesWithShieldActivated == MAX_FRAMES_WITH_SHIELD;
	}

	/**
	 * Activate the shield for the boss.
	 */
	private void activateShield() {
		isShielded = true;
	}

	public boolean isShielded() {
		return isShielded;
	}

	/**
	 * Deactivate the shield for the boss.
	 */
	public boolean deactivateShield() {
		if (isShielded) {
			isShielded = false;
			framesWithShieldActivated = 0;
			return true;
		}
		return false;
	}
}
