package com.example.demo.models;

import javafx.scene.media.AudioClip;
import java.util.Objects;

/**
 * Enemy plane sprite for the game.
 */
public class EnemyPlane extends Plane {
	private static final int IMAGE_HEIGHT = 150;
	private static final int HORIZONTAL_VELOCITY = -6;
	private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
	private static final int INITIAL_HEALTH = 1;
	private static final double FIRE_RATE = .01;
	public static final String DESTROY_ENEMY = "/com/example/demo/sounds/gameover.wav";
	private static final AudioClip destroyEnemySound = new AudioClip(Objects.requireNonNull(EnemyPlane.class.getResource(DESTROY_ENEMY)).toExternalForm());
	public static final String ENEMY_PLANE = "/com/example/demo/images/enemyplane.png";


	/**
	 * Constructor to create an instance of an EnemyPlane.
	 *
	 * @param initialXPos - the initial x coordinate position of the plane
	 * @param initialYPos - the initial y coordinate position of the plane
	 */
	public EnemyPlane(double initialXPos, double initialYPos) {
		super(ENEMY_PLANE, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
	}

	/**
	 * Update the position of the enemy plane.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Fire a projectile.
	 *
	 * @return a projectile randomly based on the fire rate, else null
	 */
	@Override
	public DestructibleSprite fireProjectile() {
		if (Math.random() < FIRE_RATE) {
			double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
			double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
			return EnemyProjectile.create(projectileXPosition, projectileYPosition);
		}
		return null;
	}

	/**
	 * Update the enemy plane.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}

	@Override
	public void takeDamage() {
		super.takeDamage();
		destroyEnemySound.play();
	}
}
