package com.example.demo;

import com.example.demo.assets.*;

public class UserProjectile extends Projectile {

	private static final int IMAGE_HEIGHT = 125;
	private static final int HORIZONTAL_VELOCITY = 15;

	public UserProjectile(double initialXPos, double initialYPos, ImageAssetManager imageManager) {
		super(AssetPaths.USER_FIRE, IMAGE_HEIGHT, initialXPos, initialYPos, imageManager);
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
