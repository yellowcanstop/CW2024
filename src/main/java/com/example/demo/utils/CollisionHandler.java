package com.example.demo.utils;

import com.example.demo.models.BossPlane;
import com.example.demo.models.DestructibleSprite;
import com.example.demo.models.UserBomb;

import java.util.List;

public class CollisionHandler {
    /**
     * Handle collisions between two lists of actors by checking if the bounds of any two actors intersect.
     * <p>
     * If the bounds of two actors intersect:
     * <p>for projectiles, the projectile is destroyed;
     * <p>for enemy planes, the enemy plane is destroyed;
     * <p>for the user plane, it has its health decremented and is destroyed if its health reaches zero;
     * <p>for the boss plane, it has its health decremented if unshielded and is destroyed if its health reaches zero;
     * <p>for level three's bomb, it deactivates the boss plane's shield if it is active.
     *
     * @param actors1 - the first list of actors
     * @param actors2 - the second list of actors
     */
    public void handleCollisions(List<DestructibleSprite> actors1,
                                 List<DestructibleSprite> actors2) {
        for (DestructibleSprite actor : actors2) {
            for (DestructibleSprite otherActor : actors1) {
                if (actor.getBoundsInParent().intersects(otherActor.getBoundsInParent())) {
                    if (actor instanceof BossPlane && otherActor instanceof UserBomb) {
                        ((BossPlane) actor).deactivateShield();
                        otherActor.takeDamage();
                    } else if (actor instanceof UserBomb && otherActor instanceof BossPlane) {
                        ((BossPlane) otherActor).deactivateShield();
                        actor.takeDamage();
                    } else {
                        actor.takeDamage();
                        otherActor.takeDamage();
                    }
                }
            }
        }
    }
}
