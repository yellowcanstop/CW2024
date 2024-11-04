package com.example.demo;

import com.example.demo.assets.*;

public class EnemyProjectile extends Projectile {

	private static final int IMAGE_HEIGHT = 50;
	private static final int HORIZONTAL_VELOCITY = -10;

	public EnemyProjectile(double initialXPos, double initialYPos, ImageAssetManager imageManager) {
		super(AssetPaths.ENEMY_FIRE, IMAGE_HEIGHT, initialXPos, initialYPos, imageManager);
	}

	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	@Override
	public void updateActor() {
		updatePosition();
	}


}
