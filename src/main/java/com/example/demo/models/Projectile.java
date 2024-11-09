package com.example.demo.models;

/**
 * Abstract class representing a projectile shot by sprites in the game.
 */
public abstract class Projectile extends DestructibleSprite {

	/**
	 * Constructor to create an instance of Projectile.
	 *
	 * @param imageName - the String classpath of the image for the projectile
	 * @param imageHeight - the height of the image for the projectile
	 * @param initialXPos - the initial x coordinate position of the projectile
	 * @param initialYPos - the initial y coordinate position of the projectile
	 */
	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
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
