package com.example.demo.models;

import com.example.demo.utils.ObjectPool;

/**
 * Projectile for the boss sprite in the game.
 */
public class BossBullet extends Projectile implements Poolable {
	private static final int IMAGE_HEIGHT = 75;
	private static final int HORIZONTAL_VELOCITY = -15;
	private static final int INITIAL_X_POSITION = 950;
	private static final ObjectPool<BossBullet> pool = new ObjectPool<>(BossBullet::new);
	public static final String FIREBALL = "/com/example/demo/images/fireball.png";

	/**
	 * Constructor to create an instance of the BossBullet class using the super constructor.
	 */
	private BossBullet() {
		super(FIREBALL, IMAGE_HEIGHT, 0, 0);
	}

	/**
	 * Get a BossBullet from the object pool and set the initial x and y position.
	 *
	 * @param initialYPos - the initial y coordinate position of the projectile
	 * @return an instance of a BossBullet
	 */
	public static BossBullet create(double initialYPos) {
		BossBullet projectile = pool.get();
		projectile.setX(INITIAL_X_POSITION);
		projectile.setY(initialYPos);
		projectile.reset();
		return projectile;
	}

	/**
	 * Release the projectile back to the object pool.
	 */
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