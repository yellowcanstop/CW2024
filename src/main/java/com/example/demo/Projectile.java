package com.example.demo;

import com.example.demo.assets.ImageAssetManager;

public abstract class Projectile extends ActiveActorDestructible {

	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos, ImageAssetManager imageManager) {
		super(imageName, imageHeight, initialXPos, initialYPos, imageManager);
	}

	@Override
	public void takeDamage() {
		this.destroy();
	}

	@Override
	public abstract void updatePosition();

}
