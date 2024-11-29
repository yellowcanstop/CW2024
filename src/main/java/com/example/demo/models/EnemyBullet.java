package com.example.demo.models;

/**
 * Projectile for the enemy sprite in the game.
 */
public class EnemyBullet extends Projectile {
	private static final int IMAGE_HEIGHT = 50;
	private static final int HORIZONTAL_VELOCITY = -10;
	public static final String ENEMY_FIRE = "/com/example/demo/images/enemyFire.png";

	/**
	 * Constructor to create an instance of the EnemyBullet class using the super constructor.
	 */
	public EnemyBullet(double initialXPos, double initialYPos) {
		super(ENEMY_FIRE, IMAGE_HEIGHT, 0, 0);
		this.setX(initialXPos);
		this.setY(initialYPos);
	}

	/**
	 * Update the position of the actor.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Update the actor.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}

	/**
	 * Called by collision handling to destroy a projectile.
	 */
	@Override
	public void takeDamage() {
		this.destroy();
	}

	/**
	 * Destroy the projectile by setting isDestroyed to true and releasing it back to the object pool.
	 */
	@Override
	public void destroy() {
		super.destroy();
		//this.release();
	}
}