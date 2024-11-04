package com.example.demo;

import com.example.demo.assets.*;

public class EnemyPlane extends FighterPlane {

	private static final int IMAGE_HEIGHT = 150;
	private static final int HORIZONTAL_VELOCITY = -6;
	private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
	private static final int INITIAL_HEALTH = 1;
	private static final double FIRE_RATE = .01;

	public EnemyPlane(double initialXPos, double initialYPos, ImageAssetManager imageManager) {
		super(AssetPaths.ENEMY_PLANE, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH, imageManager);
	}

	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	@Override
	public ActiveActorDestructible fireProjectile() {
		if (Math.random() < FIRE_RATE) {
			double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
			double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
			return new EnemyProjectile(projectileXPosition, projectileYPosition, imageManager);
		}
		return null;
	}

	@Override
	public void updateActor() {
		updatePosition();
	}

}
