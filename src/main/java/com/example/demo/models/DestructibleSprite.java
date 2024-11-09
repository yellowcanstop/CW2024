package com.example.demo.models;

/**
 * Abstract class representing a sprite which can take damage and be destroyed in the game.
 */
public abstract class DestructibleSprite extends Sprite implements Destructible {
	private boolean isDestroyed;

	/**
	 * Constructor to create an instance of DestructibleSprite.
	 *
	 * @param imageName - the String classpath of the image for the actor
	 * @param imageHeight - the height of the image for the actor
	 * @param initialXPos - the initial x coordinate position of the actor
	 * @param initialYPos - the initial y coordinate position of the actor
	 */
	public DestructibleSprite(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		isDestroyed = false;
	}

	/**
	 * Update the position of the actor.
	 */
	@Override
	public abstract void updatePosition();

	/**
	 * Update the actor.
	 */
	public abstract void updateActor();

	/**
	 * Handle damage taken by the destructible.
	 */
	@Override
	public abstract void takeDamage();

	/**
	 * Destroy the destructible.
	 */
	@Override
	public void destroy() {
		this.isDestroyed = true;
	}

	/**
	 * Check if the destructible is destroyed.
	 *
	 * @return true if destructible is destroyed, else false
	 */
	public boolean isDestroyed() {
		return isDestroyed;
	}
}
