package com.example.demo.models;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the boss plane.
 */
public class BossPlaneTest extends ApplicationTest {
    private BossPlane bossPlane;
    public static final int SCREEN_WIDTH = 1300;
    public static final int SCREEN_HEIGHT = 750;

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
        root.getChildren().add(bossPlane);
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
     * Test if Boss Plane can take damage when shield is deactivated.
     */
    @Test
    public void testBossPlaneTakingDamage() {
        int initialHealth = bossPlane.getHealth();
        bossPlane.deactivateShield();
        bossPlane.takeDamage();
        assertEquals(initialHealth - 1, bossPlane.getHealth());
    }
}