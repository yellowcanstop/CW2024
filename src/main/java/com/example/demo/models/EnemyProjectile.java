package com.example.demo.models;

import com.example.demo.assets.*;
import com.example.demo.models.Projectile;

/**
 * Projectile for the enemy sprite in the game.
 */
public class EnemyProjectile extends Projectile {

	private static final int IMAGE_HEIGHT = 50;
	private static final int HORIZONTAL_VELOCITY = -10;

	/**
	 * Constructor to create an instance of EnemyProjectile.
	 *
	 * @param initialXPos - the initial x coordinate position of the projectile
	 * @param initialYPos - the initial y coordinate position of the projectile
	 * @param imageManager - the ImageAssetManager to load the image for the projectile
	 */
	public EnemyProjectile(double initialXPos, double initialYPos, ImageAssetManager imageManager) {
		super(AssetPaths.ENEMY_FIRE, IMAGE_HEIGHT, initialXPos, initialYPos, imageManager);
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
