package com.example.demo.models;

/**
 * Abstract class representing a plane sprite in the game.
 */
public abstract class Plane extends DestructibleSprite {
	private int health;

	/**
	 * Constructor to create an instance of a Plane.
	 *
	 * @param imageName - the String classpath of the image for the plane
	 * @param imageHeight - the height of the image for the plane
	 * @param initialXPos - the initial x coordinate position of the plane
	 * @param initialYPos - the initial y coordinate position of the plane
	 * @param health - the initial health of the plane
	 */
	public Plane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.health = health;
	}

	/**
	 * Fire a projectile.
	 *
	 * @return a projectile
	 */
	public abstract DestructibleSprite fireProjectile();

	/**
	 * Handle damage taken by the destructible.
	 */
	@Override
	public void takeDamage() {
		health--;
		if (healthAtZero()) {
			this.destroy();
		}
	}

	/**
	 * Get the x position of the projectile fired by the plane.
	 *
	 * @param xPositionOffset - the offset for x position of the projectile
	 * @return the x position of the projectile fired by the plane
	 */
	protected double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}

	/**
	 * Get the y position of the projectile fired by the plane.
	 *
	 * @param yPositionOffset - the offset for y position of the projectile
	 * @return the y position of the projectile fired by the plane
	 */
	protected double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}

	/**
	 * Check if the health of the plane is at zero.
	 *
	 * @return true if the health of the plane is at zero, else false
	 */
	private boolean healthAtZero() {
		return health == 0;
	}

	/**
	 * Get the health of the plane.
	 *
	 * @return the health of the plane
	 */
	public int getHealth() {
		return health;
	}
}
