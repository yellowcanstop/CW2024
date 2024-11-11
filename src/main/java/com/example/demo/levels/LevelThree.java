package com.example.demo.levels;

import com.example.demo.models.BossPlane;
import com.example.demo.models.DestructibleSprite;
import com.example.demo.utils.AssetPaths;
import com.example.demo.views.LevelThreeView;
import javafx.scene.input.KeyCode;
import java.util.Map;

/**
 * Level three of the game.
 */
public class LevelThree extends LevelParent {
    private static final int PLAYER_INITIAL_HEALTH = 10;
    private final BossPlane bossPlane1;
    private final BossPlane bossPlane2;
    private LevelThreeView levelView;

    /**
     * Constructor to create an instance of LevelThree. Initialize the bossPlane.
     *
     * @param screenHeight - the height of the screen
     * @param screenWidth - the width of the screen
     */
    public LevelThree(double screenHeight, double screenWidth) {
        super(AssetPaths.BACKGROUND3, AssetPaths.MUSIC3, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
        bossPlane1 = new BossPlane();
        bossPlane2 = new BossPlane();
        this.levelView = new LevelThreeView(getRoot(), PLAYER_INITIAL_HEALTH);
    }

    /**
     * Initialize the units for level two: the UserPlane and the Shield for the boss.
     */
    @Override
    protected void initializeUnits() {
        getRoot().getChildren().add(getUser());
        getRoot().getChildren().add(bossPlane1.getShieldImage());
        getRoot().getChildren().add(bossPlane2.getShieldImage());
        getRoot().getChildren().add(getLevelView().getBossHealthLabel1());
        getRoot().getChildren().add(getLevelView().getBossHealthDisplay1());
        getRoot().getChildren().add(getLevelView().getBossHealthLabel2());
        getRoot().getChildren().add(getLevelView().getBossHealthDisplay2());
    }

    /**
     * Check if user is destroyed or has destroyed the bossPlane to win the game.
     */
    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        }
        else if (bossPlane1.isDestroyed() && bossPlane2.isDestroyed()) {
            winGame();
        }
    }

    /**
     * Spawn the bossPlane.
     */
    @Override
    protected void spawnEnemyUnits() {
        if (getCurrentNumberOfEnemies() == 0) {
            addEnemyUnit(bossPlane1);
            addEnemyUnit(bossPlane2);
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
        checkIfBossShielded(bossPlane1);
        checkIfBossShielded(bossPlane2);
        levelView.updateBossHealth1(bossPlane1.getHealth());
        levelView.updateBossHealth2(bossPlane2.getHealth());
    }

    /**
     * Check if the boss is shielded and show the shield if it is.
     */
    private void checkIfBossShielded(BossPlane bossPlane) {
        if (bossPlane.isShielded()) {
            bossPlane.getShieldImage().showShield();
        } else {
            bossPlane.getShieldImage().hideShield();
        }
    }
}
