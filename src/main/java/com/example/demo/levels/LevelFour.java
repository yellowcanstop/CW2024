package com.example.demo.levels;

import com.example.demo.models.BabyPlane;
import com.example.demo.models.BossPlane;
import com.example.demo.models.DestructibleSprite;
import com.example.demo.utils.FireAction;
import com.example.demo.utils.SoundLoader;
import com.example.demo.views.LevelFourView;
import javafx.scene.input.KeyCode;
import java.util.Map;

/**
 * Level four of the game.
 */
public class LevelFour extends LevelParent {
    private final LevelFourView levelView;
    private final BabyPlane babyPlane;
    private final BossPlane bossPlane1;
    private final BossPlane bossPlane2;
    private static final int PLAYER_INITIAL_HEALTH = 10;
    private static final int BABY_INITIAL_HEALTH = 5;
    private static final int BABY_INITIAL_Y_POSITION = 500;
    public static final String BACKGROUND3 = "/com/example/demo/images/levelthreeBG.png";
    public static final String MUSIC3 = "/com/example/demo/sounds/battle.wav";
    private static final long FIRE_COOL_DOWN = 1000;
    public static final String FIRE_BOMB = "/com/example/demo/sounds/firebomb.wav";

    /**
     * Constructor to create an instance of LevelThree. Initialize the boss planes.
     *
     * @param screenHeight - the height of the screen
     * @param screenWidth - the width of the screen
     */
    public LevelFour(double screenHeight, double screenWidth) {
        super(BACKGROUND3, MUSIC3, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
        babyPlane = new BabyPlane(BABY_INITIAL_Y_POSITION, BABY_INITIAL_HEALTH);
        bossPlane1 = new BossPlane();
        bossPlane2 = new BossPlane();
        this.levelView = new LevelFourView(getRoot(), PLAYER_INITIAL_HEALTH, BABY_INITIAL_HEALTH);
    }

    /**
     * Initialize units for the level.
     */
    @Override
    protected void initializeUnits() {
        super.initializeUnits();
        addFriendlyUnit(babyPlane);
        getRoot().getChildren().add(bossPlane1.getShield());
        getRoot().getChildren().add(bossPlane2.getShield());
    }

    /**
     * Initialize the level view.
     */
    @Override
    protected void initializeLevelView() {
        super.initializeLevelView();
        levelView.showBossHealthDisplay();
    }

    /**
     * Check if user is destroyed or has destroyed the boss planes to win the game.
     */
    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        }
        else if (babyIsDestroyed()) {
            babyPlane.hide();
            loseGame();
        }
        else if (bossesAreDestroyed()) {
            winGame();
        }
    }

    /**
     * Spawn the enemy units.
     */
    @Override
    protected void spawnEnemyUnits() {
        if (getCurrentNumberOfEnemies() == 0) {
            addEnemyUnit(bossPlane1);
            addEnemyUnit(bossPlane2);
        }
    }

    /**
     * Add key action to shoot bombs using TAB.
     *
     * @return the key actions
     */
    @Override
    protected Map<KeyCode, Runnable> instantiateKeyActions() {
        Map<KeyCode, Runnable> keyActions = super.instantiateKeyActions();
        keyActions.put(KeyCode.TAB, new FireAction(getRoot(), getUserProjectiles(), new SoundLoader(FIRE_BOMB), FIRE_COOL_DOWN, getUser()::fireBomb));
        return keyActions;
    }

    /**
     * Get the level view.
     *
     * @return the level view
     */
    @Override
    protected LevelFourView getLevelView() {
        return levelView;
    }

    /**
     * Update the level view and check if the boss planes are shielded.
     */
    @Override
    protected void updateLevelView() {
        super.updateLevelView();
        bossPlane1.toggleShieldVisibility();
        bossPlane2.toggleShieldVisibility();
        levelView.updateBossHealth(1, bossPlane1.getHealth(), bossPlane1.getMaxHealth());
        levelView.updateBossHealth(2, bossPlane2.getHealth(), bossPlane2.getMaxHealth());
        spawnProjectile(babyPlane.fireBullet());
        levelView.removeBabyHearts(babyPlane.getHealth());
    }

    /**
     * Display projectile fired by an enemy unit.
     *
     * @param projectile - the projectile to be displayed
     */
    public void spawnProjectile(DestructibleSprite projectile) {
        if (projectile != null) {
            getRoot().getChildren().add(projectile);
            getUserProjectiles().add(projectile);
        }
    }

    /**
     * Check if the boss planes are destroyed.
     *
     * @return true if the boss planes are destroyed, else false
     */
    boolean bossesAreDestroyed() {
        return bossPlane1.isDestroyed() && bossPlane2.isDestroyed();
    }

    /**
     * Check if the boss planes are destroyed.
     *
     * @return true if the boss planes are destroyed, else false
     */
    boolean babyIsDestroyed() {
        return babyPlane.isDestroyed();
    }
}