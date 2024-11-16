package com.example.demo.models;

import com.example.demo.utils.SoundLoader;

/**
 * Enemy plane sprite for the game.
 */
public class EnemyPlane extends Plane {
	private final SoundLoader soundLoader;
	public static final String DAMAGE_ENEMY_SOUND = "/com/example/demo/sounds/gameover.wav";
	public static final String ENEMY_PLANE = "/com/example/demo/images/enemyplane.png";
	private static final int IMAGE_HEIGHT = 150;
	private static final int HORIZONTAL_VELOCITY = -6;
	private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
	private static final int INITIAL_HEALTH = 1;
	private static final double FIRE_RATE = .01;

	/**
	 * Constructor to create an instance of an EnemyPlane.
	 *
	 * @param initialXPos - the initial x coordinate position of the plane
	 * @param initialYPos - the initial y coordinate position of the plane
	 */
	public EnemyPlane(double initialXPos, double initialYPos) {
		super(ENEMY_PLANE, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
		this.soundLoader = new SoundLoader(DAMAGE_ENEMY_SOUND);
	}

	/**
	 * Update the position of the enemy plane.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Update the enemy plane.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}

	/**
	 * Handle damage and play sound effect.
	 */
	@Override
	public void takeDamage() {
		super.takeDamage();
		soundLoader.playSound();
	}

	/**
	 * Fire a projectile.
	 *
	 * @return a projectile randomly based on the fire rate, else null
	 */
	@Override
	public DestructibleSprite fireBullet() {
		if (toFire()) {
			return createProjectile();
		}
		return null;
	}

	/**
	 * Determine if the enemy plane should fire a projectile.
	 *
	 * @return true if the enemy plane should fire a projectile, else false
	 */
	private boolean toFire() {
		return Math.random() < FIRE_RATE;
	}

	/**
	 * Create a projectile for the enemy plane.
	 *
	 * @return the projectile created
	 */
	private EnemyBullet createProjectile() {
		double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
		double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
		return EnemyBullet.create(projectileXPosition, projectileYPosition);
	}
}