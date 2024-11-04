package com.example.demo;

import com.example.demo.assets.*;

public class UserPlane extends FighterPlane {

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

	public UserPlane(int initialHealth, ImageAssetManager imageManager) {
		super(AssetPaths.USER_PLANE, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth, imageManager);
		verticalVelocityMultiplier = 0;
		horizontalVelocityMultiplier = 0;
	}
	
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
	
	@Override
	public void updateActor() {
		updatePosition();
	}
	
	@Override
	public ActiveActorDestructible fireProjectile() {
		return new UserProjectile(PROJECTILE_X_POSITION, getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET), imageManager);
	}

	private boolean isMoving() {
		return verticalVelocityMultiplier != 0 || horizontalVelocityMultiplier != 0;
	}

	public void moveUp() {
		verticalVelocityMultiplier = -1;
	}

	public void moveDown() {
		verticalVelocityMultiplier = 1;
	}

	public void moveLeft() {
		horizontalVelocityMultiplier = -1;
	}
	public void moveRight() {
		horizontalVelocityMultiplier = 1;
	}

	public void stop() {
		verticalVelocityMultiplier = 0;
		horizontalVelocityMultiplier = 0;
	}

	public int getNumberOfKills() {
		return numberOfKills;
	}

	public void incrementKillCount() {
		numberOfKills++;
	}

}
