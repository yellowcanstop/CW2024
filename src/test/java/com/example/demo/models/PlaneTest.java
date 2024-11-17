package com.example.demo.models;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.junit.jupiter.api.Assertions.*;

public class PlaneTest extends ApplicationTest {
    private BossPlane bossPlane;
    private UserPlane userPlane;
    private EnemyPlane enemyPlane;

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        bossPlane = new BossPlane();
        userPlane = new UserPlane(5);
        enemyPlane = new EnemyPlane(1300, 600);
        root.getChildren().addAll(bossPlane, userPlane, enemyPlane);
        stage.setScene(new Scene(root, 1300, 750));
        stage.show();
    }

    @Test
    public void testBossPlaneHasShield() {
        assertNotNull(bossPlane.getShield());
    }

    @Test
    public void testEnemyPlaneMovement() {
        double initialX = enemyPlane.getTranslateX();
        enemyPlane.updatePosition();
        assertNotEquals(initialX, enemyPlane.getTranslateX());
    }

    @Test
    public void testUserPlaneFireBullet() {
        DestructibleSprite bullet = userPlane.fireBullet();
        assertNotNull(bullet);
        assertInstanceOf(UserBullet.class, bullet);
    }

    @Test
    public void testUserPlaneFireBomb() {
        DestructibleSprite bomb = userPlane.fireBomb();
        assertNotNull(bomb);
        assertInstanceOf(UserBomb.class, bomb);
    }

    @Test
    public void testBossPlaneTakingDamage() {
        int initialHealth = bossPlane.getHealth();
        bossPlane.deactivateShield();
        bossPlane.takeDamage();
        assertEquals(initialHealth - 1, bossPlane.getHealth());
    }

    @Test
    public void testUserPlaneTakingDamage() {
        int initialHealth = userPlane.getHealth();
        userPlane.takeDamage();
        assertEquals(initialHealth - 1, userPlane.getHealth());
    }

    @Test
    public void testEnemyPlaneTakingDamage() {
        int initialHealth = enemyPlane.getHealth();
        enemyPlane.takeDamage();
        assertEquals(initialHealth - 1, enemyPlane.getHealth());
    }
}