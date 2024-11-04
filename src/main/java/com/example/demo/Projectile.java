package com.example.demo;

import com.example.demo.assets.ImageAssetManager;

/**
 * Abstract class representing a projectile shot by sprites in the game.
 */
public abstract class Projectile extends ActiveActorDestructible {

	/**
	 * Constructor to create an instance of Projectile.
	 *
	 * @param imageName - the String classpath of the image for the projectile
	 * @param imageHeight - the height of the image for the projectile
	 * @param initialXPos - the initial x coordinate position of the projectile
	 * @param initialYPos - the initial y coordinate position of the projectile
	 * @param imageManager - the ImageAssetManager to load the image for the projectile
	 */
	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos, ImageAssetManager imageManager) {
		super(imageName, imageHeight, initialXPos, initialYPos, imageManager);
	}

	/**
	 * Handle damage taken by the destructible.
	 */
	@Override
	public void takeDamage() {
		this.destroy();
	}

	/**
	 * Update the position of the actor.
	 */
	@Override
	public abstract void updatePosition();

}
