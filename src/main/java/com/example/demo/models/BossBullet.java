package com.example.demo.models;

/**
 * Projectile for the boss sprite in the game.
 */
public class BossBullet extends Projectile {
	private static final int IMAGE_HEIGHT = 75;
	private static final int HORIZONTAL_VELOCITY = -15;
	private static final int INITIAL_X_POSITION = 950;
	public static final String FIREBALL = "/com/example/demo/images/fireball.png";

	/**
	 * Constructor to create an instance of the BossBullet class using the super constructor.
	 */
	public BossBullet(double initialYPos) {
		super(FIREBALL, IMAGE_HEIGHT, 0, 0);
		this.setX(INITIAL_X_POSITION);
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