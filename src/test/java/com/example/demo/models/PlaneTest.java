package com.example.demo.models;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the BossPlane, UserPlane and EnemyPlane classes.
 */
public class PlaneTest extends ApplicationTest {
    private BossPlane bossPlane;
    private UserPlane userPlane;
    private EnemyPlane enemyPlane;
    public static final int SCREEN_WIDTH = 1300;
    public static final int SCREEN_HEIGHT = 750;
    public static final int USER_HEALTH = 5;
    public static final int ENEMY_Y_POSITION = 600;

    /**
     * Start method for the JavaFX application, required for ApplicationTest.
     * <p>
     * If TestFX is not used, throws RuntimeException: Internal graphics not initialized yet.
     *
     * @param stage - the primary stage for the JavaFX application
     */
    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        bossPlane = new BossPlane();
        userPlane = new UserPlane(USER_HEALTH);
        enemyPlane = new EnemyPlane(SCREEN_WIDTH, ENEMY_Y_POSITION);
        root.getChildren().addAll(bossPlane, userPlane, enemyPlane);
        stage.setScene(new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT));
        stage.show();
    }

    /**
     * Test if Boss Plane has shield.
     */
    @Test
    public void testBossPlaneHasShield() {
        assertNotNull(bossPlane.getShield());
    }

    /**
     * Test if Enemy Plane moves.
     */
    @Test
    public void testEnemyPlaneMovement() {
        double initialX = enemyPlane.getTranslateX();
        enemyPlane.updatePosition();
        assertNotEquals(initialX, enemyPlane.getTranslateX());
    }

    /**
     * Test if User Plane can fire bullet.
     */
    @Test
    public void testUserPlaneFireBullet() {
        DestructibleSprite bullet = userPlane.fireBullet();
        assertNotNull(bullet);
        assertInstanceOf(UserBullet.class, bullet);
    }

    /**
     * Test if User Plane can fire bomb.
     */
    @Test
    public void testUserPlaneFireBomb() {
        DestructibleSprite bomb = userPlane.fireBomb();
        assertNotNull(bomb);
        assertInstanceOf(UserBomb.class, bomb);
    }

    /**
     * Test if Boss Plane can take damage when shield is deactivated.
     */
    @Test
    public void testBossPlaneTakingDamage() {
        int initialHealth = bossPlane.getHealth();
        bossPlane.deactivateShield();
        bossPlane.takeDamage();
        assertEquals(initialHealth - 1, bossPlane.getHealth());
    }

    /**
     * Test if User Plane can take damage.
     */
    @Test
    public void testUserPlaneTakingDamage() {
        int initialHealth = userPlane.getHealth();
        userPlane.takeDamage();
        assertEquals(initialHealth - 1, userPlane.getHealth());
    }

    /**
     * Test if Enemy Plane can take damage.
     */
    @Test
    public void testEnemyPlaneTakingDamage() {
        int initialHealth = enemyPlane.getHealth();
        enemyPlane.takeDamage();
        assertEquals(initialHealth - 1, enemyPlane.getHealth());
    }
}