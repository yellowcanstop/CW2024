package com.example.demo.models;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the enemy plane.
 */
public class EnemyPlaneTest extends ApplicationTest {
    private EnemyPlane enemyPlane;
    public static final int SCREEN_WIDTH = 1300;
    public static final int SCREEN_HEIGHT = 750;
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
        enemyPlane = new EnemyPlane(SCREEN_WIDTH, ENEMY_Y_POSITION);
        root.getChildren().add(enemyPlane);
        stage.setScene(new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT));
        stage.show();
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
     * Test if Enemy Plane can take damage.
     */
    @Test
    public void testEnemyPlaneTakingDamage() {
        int initialHealth = enemyPlane.getHealth();
        enemyPlane.takeDamage();
        assertEquals(initialHealth - 1, enemyPlane.getHealth());
    }
}