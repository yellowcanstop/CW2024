package com.example.demo;

import com.example.demo.assets.*;

public class BossProjectile extends Projectile {

	private static final int IMAGE_HEIGHT = 75;
	private static final int HORIZONTAL_VELOCITY = -15;
	private static final int INITIAL_X_POSITION = 950;

	public BossProjectile(double initialYPos, ImageAssetManager imageManager) {
		super(AssetPaths.FIREBALL, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos, imageManager);
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
