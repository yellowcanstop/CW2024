package com.example.demo.levels;

import com.example.demo.models.BossPlane;
import com.example.demo.models.DestructibleSprite;
import com.example.demo.utils.AssetPaths;
import com.example.demo.views.LevelThreeView;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Level three of the game.
 */
public class LevelThree extends LevelParent {
    private static final int PLAYER_INITIAL_HEALTH = 10;
    private final BossPlane bossPlane;
    private LevelThreeView levelView;

    /**
     * Constructor to create an instance of LevelThree. Initialize the bossPlane.
     *
     * @param screenHeight - the height of the screen
     * @param screenWidth - the width of the screen
     */
    public LevelThree(double screenHeight, double screenWidth) {
        super(AssetPaths.BACKGROUND1, AssetPaths.MUSIC1, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
        bossPlane = new BossPlane();
        this.levelView = new LevelThreeView(getRoot(), PLAYER_INITIAL_HEALTH);
    }

    /**
     * Initialize the units for level two: the UserPlane and the Shield for the boss.
     */
    @Override
    protected void initializeUnits() {
        getRoot().getChildren().add(getUser());
        getRoot().getChildren().add(bossPlane.getShieldImage());
        getRoot().getChildren().add(getLevelView().getBossHealthLabel());
        getRoot().getChildren().add(getLevelView().getBossHealthDisplay());
    }

    /**
     * Check if user is destroyed or has destroyed the bossPlane to win the game.
     */
    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        }
        else if (bossPlane.isDestroyed()) {
            winGame();
        }
    }

    /**
     * Spawn the bossPlane.
     */
    @Override
    protected void spawnEnemyUnits() {
        if (getCurrentNumberOfEnemies() == 0) {
            addEnemyUnit(bossPlane);
        }
    }

	@Override
    protected Map<KeyCode, Runnable> instantiateKeyActions() {
        Map<KeyCode, Runnable> keyActions = super.instantiateKeyActions();
        keyActions.put(KeyCode.TAB, this::fireBombs);
        return keyActions;
    }

    /**
     * Fire a projectile from the plane controlled by the player.
     */
    private void fireBombs() {
        DestructibleSprite bomb = getUser().fireBombs();
        getRoot().getChildren().add(bomb);
        getUserProjectiles().add(bomb);
    }

    /**
     * Instantiate the level view with all nodes and the player's initial health.
     *
     * @return the level view
     */
    @Override
    protected LevelThreeView getLevelView() {
        return levelView;
    }

    /**
     * Update the level view and check if the boss is shielded.
     */
    @Override
    protected void updateLevelView() {
        super.updateLevelView();
        checkIfBossShielded();
        levelView.updateBossHealth(bossPlane.getHealth());
    }

    /**
     * Check if the boss is shielded and show the shield if it is.
     */
    private void checkIfBossShielded() {
        if (bossPlane.isShielded()) {
            bossPlane.getShieldImage().showShield();
        } else {
            bossPlane.getShieldImage().hideShield();
        }
    }
}
