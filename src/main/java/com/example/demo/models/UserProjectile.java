package com.example.demo.models;


/**
 * Projectile for user-controlled player sprite in the game.
 */
public class UserProjectile extends Projectile {
	private static final int IMAGE_HEIGHT = 125;
	private static final int HORIZONTAL_VELOCITY = 15;
	public static final String USER_FIRE = "/com/example/demo/images/userfire.png";


	/**
	 * Constructor to create an instance of UserProjectile.
	 *
	 * @param initialXPos - the initial x coordinate position of the projectile
	 * @param initialYPos - the initial y coordinate position of the projectile
	 */
	public UserProjectile(double initialXPos, double initialYPos) {
		super(USER_FIRE, IMAGE_HEIGHT, initialXPos, initialYPos);
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
}
