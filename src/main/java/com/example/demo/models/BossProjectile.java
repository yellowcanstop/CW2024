package com.example.demo.models;

import com.example.demo.utils.ObjectPool;

/**
 * Projectile for the boss sprite in the game.
 */
public class BossProjectile extends Projectile {
	private static final int IMAGE_HEIGHT = 75;
	private static final int HORIZONTAL_VELOCITY = -15;
	private static final int INITIAL_X_POSITION = 950;
	private static final ObjectPool<BossProjectile> pool = new ObjectPool<>(BossProjectile::new);
	public static final String FIREBALL = "/com/example/demo/images/fireball.png";

	/**
	 * Constructor to create an instance of BossProjectile.
	 *
	 */
	private BossProjectile() {
		super(FIREBALL, IMAGE_HEIGHT, 0, 0);
	}

	public static BossProjectile create(double initialYPos) {
		BossProjectile projectile = pool.get();
		projectile.setX(INITIAL_X_POSITION);
		projectile.setY(initialYPos);
		projectile.isDestroyed = false;
		return projectile;
	}

	/**
	 * Release the EnemyProjectile back to the object pool.
	 */
	public void release() {
		pool.release(this);
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
