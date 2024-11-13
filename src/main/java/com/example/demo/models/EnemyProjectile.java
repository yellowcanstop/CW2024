package com.example.demo.models;

import com.example.demo.utils.AssetPaths;
import com.example.demo.utils.ObjectPool;

/**
 * Projectile for the enemy sprite in the game.
 */
public class EnemyProjectile extends Projectile {
	private static final int IMAGE_HEIGHT = 50;
	private static final int HORIZONTAL_VELOCITY = -10;
	private static final ObjectPool<EnemyProjectile> pool = new ObjectPool<>(EnemyProjectile::new);

	/**
	 * Constructor to create an instance of the EnemyProjectile class using the super constructor.
	 */
	private EnemyProjectile() {
		super(AssetPaths.ENEMY_FIRE, IMAGE_HEIGHT, 0, 0);
	}

	/**
	 * Get an EnemyProjectile from the object pool and set the initial x and y position.
	 *
	 * @param initialXPos - the initial x coordinate position of the projectile
	 * @param initialYPos - the initial y coordinate position of the projectile
	 * @return an instance of an EnemyProjectile
	 */
	public static EnemyProjectile create(double initialXPos, double initialYPos) {
		EnemyProjectile projectile = pool.get();
		projectile.setX(initialXPos);
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
