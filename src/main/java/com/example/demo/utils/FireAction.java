package com.example.demo.utils;

import com.example.demo.models.DestructibleSprite;
import javafx.scene.Group;
import java.util.List;

/**
 * Generalized action to fire a projectile (UserProjectile or UserBomb) in the game.
 * <p>
 * Encapsulates the logic that a new shot is only allowed if the time since the last shot is greater than the cool-down time.
 */
public class FireAction implements Runnable {
    private final Group root;
    private final List<DestructibleSprite> projectiles;
    private final SoundLoader soundLoader;
    private final long coolDown;
    private final Fireable fireable;
    private long lastFireTime;

    /**
     * Constructor to create an instance of FireAction.
     *
     * @param root - the group of all nodes to be displayed in the scene
     * @param projectiles - the list of all projectiles in the scene
     * @param soundLoader - the sound loader to play the sound of the projectile
     * @param coolDown - the cool-down time between shots
     * @param fireable - a fireable object which can use a fire method to return a projectile
     */
    public FireAction(Group root, List<DestructibleSprite> projectiles, SoundLoader soundLoader, long coolDown, Fireable fireable) {
        this.root = root;
        this.projectiles = projectiles;
        this.soundLoader = soundLoader;
        this.coolDown = coolDown;
        this.fireable = fireable;
        this.lastFireTime = 0;
    }

    /**
     * Run as specified by the Runnable interface.
     * <p>
     * Fire a projectile if the time since the last shot is greater than the cool-down time.
     * <p>
     * Plays the sound effect of firing the projectile.
     */
    @Override
    public void run() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastFireTime >= coolDown) {
            DestructibleSprite projectile = fireable.fire();
            root.getChildren().add(projectile);
            projectiles.add(projectile);
            lastFireTime = currentTime;
            soundLoader.playSound();
        }
    }

    /**
     * Inner interface to represent a fireable object which can use a fire method to return a projectile.
     */
    public interface Fireable {
        DestructibleSprite fire();
    }
}