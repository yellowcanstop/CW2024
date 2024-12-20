package com.example.demo.models;

import com.example.demo.utils.ObjectPool;

/**
 * Projectile for the enemy sprite in the game.
 */
public class EnemyBullet extends Projectile implements Poolable {
	private static final int IMAGE_HEIGHT = 50;
	private static final int HORIZONTAL_VELOCITY = -10;
	private static final ObjectPool<EnemyBullet> pool = new ObjectPool<>(EnemyBullet::new);
	public static final String ENEMY_FIRE = "/com/example/demo/images/enemyFire.png";

	/**
	 * Constructor to create an instance of the EnemyBullet class using the super constructor.
	 */
	private EnemyBullet() {
		super(ENEMY_FIRE, IMAGE_HEIGHT, 0, 0);
	}

	/**
	 * Get an EnemyBullet from the object pool and set the initial x and y position.
	 *
	 * @param initialXPos - the initial x coordinate position of the projectile
	 * @param initialYPos - the initial y coordinate position of the projectile
	 * @return an instance of an EnemyBullet
	 */
	public static EnemyBullet create(double initialXPos, double initialYPos) {
		EnemyBullet projectile = pool.get();
		projectile.setX(initialXPos);
		projectile.setY(initialYPos);
		projectile.reset();
		return projectile;
	}

	/**
	 * Reset the object pool. Called when game is lost or won.
	 */
	public static void resetPool() {
		pool.resetPool();
	}

	/**
	 * Release the projectile back to the object pool.
	 */
	@Override
	public void release() {
		pool.release(this);
	}

	/**
	 * Reset the projectile by setting isDestroyed to false.
	 */
	@Override
	public void reset() {
		resetIsDestroyed();
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
		this.release();
	}
}