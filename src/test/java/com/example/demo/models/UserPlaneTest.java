package com.example.demo.models;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the user plane.
 */
public class UserPlaneTest extends ApplicationTest {
    private UserPlane userPlane;
    public static final int SCREEN_WIDTH = 1300;
    public static final int SCREEN_HEIGHT = 750;
    public static final int USER_HEALTH = 5;

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
        userPlane = new UserPlane(USER_HEALTH);
        root.getChildren().add(userPlane);
        stage.setScene(new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT));
        stage.show();
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
     * Test if User Plane can take damage.
     */
    @Test
    public void testUserPlaneTakingDamage() {
        int initialHealth = userPlane.getHealth();
        userPlane.takeDamage();
        assertEquals(initialHealth - 1, userPlane.getHealth());
    }
}