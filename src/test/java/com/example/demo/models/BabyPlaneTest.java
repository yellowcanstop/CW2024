package com.example.demo.models;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the baby plane.
 */
public class BabyPlaneTest extends ApplicationTest {
    private BabyPlane babyPlane;
    public static final int SCREEN_WIDTH = 1300;
    public static final int SCREEN_HEIGHT = 750;
    private static final int BABY_INITIAL_HEALTH = 5;
    private static final int BABY_INITIAL_Y_POSITION = 500;

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
        babyPlane = new BabyPlane(BABY_INITIAL_HEALTH, BABY_INITIAL_Y_POSITION);
        root.getChildren().add(babyPlane);
        stage.setScene(new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT));
        stage.show();
    }

    /**
     * Test if Baby Plane can take damage.
     */
    @Test
    public void testBabyPlaneTakingDamage() {
        int initialHealth = babyPlane.getHealth();
        babyPlane.takeDamage();
        assertEquals(initialHealth - 1, babyPlane.getHealth());
    }
}