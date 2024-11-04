package com.example.demo;

import com.example.demo.assets.*;

/**
 * Projectile for the boss sprite in the game.
 */
public class BossProjectile extends Projectile {

	private static final int IMAGE_HEIGHT = 75;
	private static final int HORIZONTAL_VELOCITY = -15;
	private static final int INITIAL_X_POSITION = 950;

	/**
	 * Constructor to create an instance of BossProjectile.
	 *
	 * @param initialYPos - the initial y coordinate position of the projectile
	 * @param imageManager - the ImageAssetManager to load the image for the projectile
	 */
	public BossProjectile(double initialYPos, ImageAssetManager imageManager) {
		super(AssetPaths.FIREBALL, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos, imageManager);
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
