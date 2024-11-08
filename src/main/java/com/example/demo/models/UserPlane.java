package com.example.demo.models;

import com.example.demo.assets.*;

/**
 * User-controlled plane sprite for the game.
 */
public class UserPlane extends Plane {

	private static final double X_UPPER_BOUND = -40;
	private static final double X_LOWER_BOUND = 800;
	private static final double Y_UPPER_BOUND = -40;
	private static final double Y_LOWER_BOUND = 600.0;
	private static final double INITIAL_X_POSITION = 5.0;
	private static final double INITIAL_Y_POSITION = 300.0;
	private static final int IMAGE_HEIGHT = 150;
	private static final int VERTICAL_VELOCITY = 8;
	private static final int HORIZONTAL_VELOCITY = 8;
	private static final int PROJECTILE_X_POSITION = 110;
	private static final int PROJECTILE_Y_POSITION_OFFSET = 20;
	private int verticalVelocityMultiplier;
	private int horizontalVelocityMultiplier;
	private int numberOfKills;

	/**
	 * Constructor to create an instance of a UserPlane.
	 *
	 * @param initialHealth - the initial health of the player
	 * @param imageManager - the ImageAssetManager to load the image for the user plane
	 */
	public UserPlane(int initialHealth, ImageAssetManager imageManager) {
		super(AssetPaths.USER_PLANE, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth, imageManager);
		verticalVelocityMultiplier = 0;
		horizontalVelocityMultiplier = 0;
	}

	/**
	 * Update the position of the user plane if it is moving.
	 */
	@Override
	public void updatePosition() {
		if (isMoving()) {
			double initialTranslateY = getTranslateY();
			double initialTranslateX = getTranslateX();
			this.moveVertically(VERTICAL_VELOCITY * verticalVelocityMultiplier);
			this.moveHorizontally(HORIZONTAL_VELOCITY * horizontalVelocityMultiplier);
			double newYPosition = getLayoutY() + getTranslateY();
			double newXPosition = getLayoutX() + getTranslateX();
			if (newYPosition < Y_UPPER_BOUND || newYPosition > Y_LOWER_BOUND) {
				this.setTranslateY(initialTranslateY);
			}
			if (newXPosition < X_UPPER_BOUND || newXPosition > X_LOWER_BOUND) {
				this.setTranslateX(initialTranslateX);
			}
		}
	}

	/**
	 * Update the user plane.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}

	/**
	 * Fire a projectile.
	 *
	 * @return a projectile
	 */
	@Override
	public DestructibleSprite fireProjectile() {
		return new UserProjectile(PROJECTILE_X_POSITION, getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET), imageManager);
	}

	/**
	 * Check if the user plane is moving.
	 *
	 * @return true if the user plane is moving, else false
	 */
	private boolean isMoving() {
		return verticalVelocityMultiplier != 0 || horizontalVelocityMultiplier != 0;
	}

	/**
	 * Move the plane upward.
	 */
	public void moveUp() {
		verticalVelocityMultiplier = -1;
	}

	/**
	 * Move the plane downward.
	 */
	public void moveDown() {
		verticalVelocityMultiplier = 1;
	}

	/**
	 * Move the plane leftward.
	 */
	public void moveLeft() {
		horizontalVelocityMultiplier = -1;
	}

	/**
	 * Move the plane rightward.
	 */
	public void moveRight() {
		horizontalVelocityMultiplier = 1;
	}

	/**
	 * Stop the plane from moving.
	 */
	public void stop() {
		verticalVelocityMultiplier = 0;
		horizontalVelocityMultiplier = 0;
	}

	/**
	 * Get the kill count achieved by the user.
	 *
	 * @return the number of kills
	 */
	public int getNumberOfKills() {
		return numberOfKills;
	}

	/**
	 * Increment the kill count.
	 */
	public void incrementKillCount() {
		numberOfKills++;
	}

}