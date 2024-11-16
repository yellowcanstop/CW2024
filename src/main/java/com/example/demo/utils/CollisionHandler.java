package com.example.demo.utils;

import com.example.demo.models.BossPlane;
import com.example.demo.models.DestructibleSprite;
import com.example.demo.models.UserBomb;
import java.util.List;

public class CollisionHandler {
    public static final String DEACTIVATE_SHIELD = "/com/example/demo/sounds/minty_attack.wav";
    private final SoundLoader soundLoader = new SoundLoader(DEACTIVATE_SHIELD);

    /**
     * Handle collisions between two lists of actors by checking if collision condition met for each pair of actors.
     *
     * @param actors - the first list of actors
     * @param otherActors - the second list of actors
     */
    public void handleCollisions(List<DestructibleSprite> actors, List<DestructibleSprite> otherActors) {
        for (DestructibleSprite actor : actors) {
            for (DestructibleSprite otherActor : otherActors) {
                if (collided(actor, otherActor)) {
                    handleCollision(actor, otherActor);
                }
            }
        }
    }

    /**
     * Check if collision has occurred by checking if the bounds of the two actors intersect.
     *
     * @param actor - the first actor
     * @param otherActor - the second actor
     * @return true if the actors have collided, else false
     */
    private boolean collided(DestructibleSprite actor, DestructibleSprite otherActor) {
        return actor.getBoundsInParent().intersects(otherActor.getBoundsInParent());
    }

    /**
     * Handle the collision between two actors by taking damage from each actor.
     * <p>
     * Special case for collision between boss and user bomb by calling handleBombCollisionWithBoss.
     *
     * @param actor - the first actor
     * @param otherActor - the second actor
     */
    private void handleCollision(DestructibleSprite actor, DestructibleSprite otherActor) {
        if (actor instanceof BossPlane && otherActor instanceof UserBomb) {
            handleBombCollisionWithBoss((BossPlane) actor, (UserBomb) otherActor);
            return;
        }
        if (actor instanceof UserBomb && otherActor instanceof BossPlane) {
            handleBombCollisionWithBoss((BossPlane) otherActor, (UserBomb) actor);
            return;
        }
        actor.takeDamage();
        otherActor.takeDamage();
    }

    /**
     * Handle the collision between boss and user bomb by deactivating the boss shield, playing a sound effect and cleaning the bomb.
     *
     * @param bossPlane - the boss plane
     * @param userBomb - the bomb fired by the user
     */
    private void handleBombCollisionWithBoss(BossPlane bossPlane, UserBomb userBomb) {
        boolean hit = bossPlane.deactivateShield();
        if (hit) {
            soundLoader.playSound();
        }
        userBomb.takeDamage();
    }
}