package com.example.demo.models;

import com.example.demo.utils.SoundLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Baby plane sprite for the game.
 */
public class BabyPlane extends Plane {
    private final SoundLoader soundLoader;
    public static final String DAMAGE_BABY_SOUND = "/com/example/demo/sounds/ugh.mp3";
    public static final String BABY_PLANE = "/com/example/demo/images/userplane.png";
    private static final int IMAGE_HEIGHT = 70;
    private static final int Y_POSITION_UPPER_BOUND = 120;
    private static final int Y_POSITION_LOWER_BOUND = 560;
    private static final int INITIAL_X_POSITION = 40;
    private static final int PROJECTILE_X_POSITION_OFFSET = 70;
    private static final int PROJECTILE_Y_POSITION_OFFSET = 20;
    private static final double FIRE_RATE = .08;
    private final List<Integer> movePattern;
    private static final int VERTICAL_VELOCITY = 15;
    private static final int MOVE_FREQUENCY_PER_CYCLE = 10;
    private static final int ZERO = 0;
    private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;
    private int consecutiveMovesInSameDirection;
    private int indexOfCurrentMove;

    /**
     * Constructor to create an instance of a BabyPlane.
     *
     * @param initialYPos - the initial y coordinate position of the plane
     * @param health - the health of the plane
     */
    public BabyPlane(double initialYPos, int health) {
        super(BABY_PLANE, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos, health);
        this.soundLoader = new SoundLoader(DAMAGE_BABY_SOUND);
        movePattern = new ArrayList<>();
        consecutiveMovesInSameDirection = 0;
        indexOfCurrentMove = 0;
        initializeMovePattern();
    }

    /**
     * Update the position of the baby plane.
     */
    @Override
    public void updatePosition() {
        double initialTranslateY = getTranslateY();
        moveVertically(getNextMove());
        double currentPosition = getLayoutY() + getTranslateY();
        if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
            setTranslateY(initialTranslateY);
        }
    }

    /**
     * Update the baby plane.
     */
    @Override
    public void updateActor() {
        updatePosition();
    }

    /**
     * Handle damage and play sound effect.
     */
    @Override
    public void takeDamage() {
        super.takeDamage();
        soundLoader.playSound();
    }

    /**
     * Fire a projectile.
     *
     * @return a projectile randomly based on the fire rate, else null
     */
    @Override
    public DestructibleSprite fireBullet() {
        if (toFire()) {
            return new UserBullet(INITIAL_X_POSITION + PROJECTILE_X_POSITION_OFFSET, getProjectileInitialPosition());
        }
        return null;
    }

    /**
     * Hide the baby plane.
     */
    public void hide() {
        this.setVisible(false);
    }

    /**
     * Determine if the baby plane should fire a projectile.
     *
     * @return true if the baby plane should fire a projectile, else false
     */
    private boolean toFire() {
        return Math.random() < FIRE_RATE;
    }

    /**
     * Initialize the random movement pattern for the baby plane.
     */
    private void initializeMovePattern() {
        for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
            movePattern.add(VERTICAL_VELOCITY);
            movePattern.add(-VERTICAL_VELOCITY);
            movePattern.add(ZERO);
        }
        Collections.shuffle(movePattern);
    }

    /**
     * Get the next move for the baby plane.
     *
     * @return the integer value of the next move
     */
    private int getNextMove() {
        int currentMove = movePattern.get(indexOfCurrentMove);
        consecutiveMovesInSameDirection++;
        if (consecutiveMovesInSameDirection == MAX_FRAMES_WITH_SAME_MOVE) {
            Collections.shuffle(movePattern);
            consecutiveMovesInSameDirection = 0;
            indexOfCurrentMove++;
        }
        if (indexOfCurrentMove == movePattern.size()) {
            indexOfCurrentMove = 0;
        }
        return currentMove;
    }

    /**
     * Get the initial Y coordinate position for the projectile fired by the baby plane.
     *
     * @return the initial Y coordinate position for the projectile fired by the baby plane
     */
    private double getProjectileInitialPosition() {
        return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
    }
}