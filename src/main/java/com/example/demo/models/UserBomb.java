package com.example.demo.models;

import com.example.demo.utils.AssetPaths;

/**
 * Bomb for user-controlled player sprite in the game.
 */
public class UserBomb extends Projectile {
    private static final int IMAGE_HEIGHT = 125;
    private static final int HORIZONTAL_VELOCITY = 15;

    /**
     * Constructor to create an instance of UserBomb.
     *
     * @param initialXPos - the initial x coordinate position of the projectile
     * @param initialYPos - the initial y coordinate position of the projectile
     */
    public UserBomb(double initialXPos, double initialYPos) {
        super(AssetPaths.USER_BOMB, IMAGE_HEIGHT, initialXPos, initialYPos);
    }

    /**
     * Update the position of the actor.
     */
    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }

    /**
     * Update the actor.
     */
    @Override
    public void updateActor() {
        updatePosition();
    }
}
