package com.example.demo.models;


/**
 * Bomb for user-controlled player sprite in the game.
 */
public class UserBomb extends Projectile {
    private static final int IMAGE_HEIGHT = 50;
    private static final int HORIZONTAL_VELOCITY = 20;
    public static final String USER_BOMB = "/com/example/demo/images/userfire2.png";

    /**
     * Constructor to create an instance of UserBomb.
     *
     * @param initialXPos - the initial x coordinate position of the projectile
     * @param initialYPos - the initial y coordinate position of the projectile
     */
    public UserBomb(double initialXPos, double initialYPos) {
        super(USER_BOMB, IMAGE_HEIGHT, initialXPos, initialYPos);
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
