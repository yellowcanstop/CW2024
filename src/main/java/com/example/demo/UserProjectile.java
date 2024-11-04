package com.example.demo;

import com.example.demo.assets.*;

/**
 * Projectile for user-controlled player sprite in the game.
 */
public class UserProjectile extends Projectile {

	private static final int IMAGE_HEIGHT = 125;
	private static final int HORIZONTAL_VELOCITY = 15;

	/**
	 * Constructor to create an instance of UserProjectile.
	 *
	 * @param initialXPos - the initial x coordinate position of the projectile
	 * @param initialYPos - the initial y coordinate position of the projectile
	 * @param imageManager - the ImageAssetManager to load the image for the projectile
	 */
	public UserProjectile(double initialXPos, double initialYPos, ImageAssetManager imageManager) {
		super(AssetPaths.USER_FIRE, IMAGE_HEIGHT, initialXPos, initialYPos, imageManager);
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
