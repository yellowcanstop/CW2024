package com.example.demo.models;

/**
 * User-controlled plane sprite for the game.
 */
public class UserPlane extends Plane implements InputControlledObject {
	private static final double X_UPPER_BOUND = -40;
	private static final double X_LOWER_BOUND = 500;
	private static final double Y_UPPER_BOUND = -10;
	private static final double Y_LOWER_BOUND = 600.0;
	private static final double INITIAL_X_POSITION = 10.0;
	private static final double INITIAL_Y_POSITION = 300.0;
	private static final int IMAGE_HEIGHT = 120;
	private static final int VERTICAL_VELOCITY = 10;
	private static final int HORIZONTAL_VELOCITY = 10;
	private static final int PROJECTILE_X_POSITION_OFFSET = 20;
	private static final int PROJECTILE_Y_POSITION_OFFSET = 20;
	public static final String USER_PLANE = "/com/example/demo/images/userplane.png";
	private int verticalVelocityMultiplier;
	private int horizontalVelocityMultiplier;
	private int numberOfKills;

	/**
	 * Constructor to create an instance of a UserPlane.
	 *
	 * @param initialHealth - the initial health of the player
	 */
	public UserPlane(int initialHealth) {
		super(USER_PLANE, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
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
	public DestructibleSprite fireBullet() {
		return new UserBullet(getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET), getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
	}

	/**
	 * Fire a bomb.
	 *
	 * @return a bomb
	 */
	public DestructibleSprite fireBomb() {
		return new UserBomb(getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET), getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
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

	/**
	 * Check if the user plane is moving.
	 *
	 * @return true if the user plane is moving, else false
	 */
	private boolean isMoving() {
		return verticalVelocityMultiplier != 0 || horizontalVelocityMultiplier != 0;
	}
}