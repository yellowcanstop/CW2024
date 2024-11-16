package com.example.demo.views;

import com.example.demo.views.components.ProgressDisplay;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

/**
 * View for level three of the game.
 */
public class LevelThreeView extends LevelView {
    private final Group root;
    private final ProgressDisplay bossHealthDisplay1;
    private final ProgressDisplay bossHealthDisplay2;
    private static final int BOSS_HEALTH_X_POSITION = 1000;
    private static final int BOSS_HEALTH_Y_POSITION = 20;
    private static final int BOSS_HEALTH_DIVISOR = 10;

    /**
     * Constructor to create an instance of the view for Level Two.
     *
     * @param root - group of all nodes to be displayed in the scene
     * @param heartsToDisplay - the initial number of hearts to be displayed
     */
    public LevelThreeView(Group root, int heartsToDisplay) {
        super(root, heartsToDisplay);
        this.root = root;
        this.bossHealthDisplay1 = new ProgressDisplay(BOSS_HEALTH_X_POSITION, BOSS_HEALTH_Y_POSITION, "Boss Health 1");
        this.bossHealthDisplay2 = new ProgressDisplay(BOSS_HEALTH_X_POSITION, BOSS_HEALTH_Y_POSITION + 50, "Boss Health 2");
    }

    /**
     * Show the boss health display.
     */
    public void showBossHealthDisplay() {
        root.getChildren().add(bossHealthDisplay1.getProgressLabel());
        root.getChildren().add(bossHealthDisplay1.getProgressBar());
        root.getChildren().add(bossHealthDisplay2.getProgressLabel());
        root.getChildren().add(bossHealthDisplay2.getProgressBar());
    }

    /**
     * Update display to show the boss health (percentage between 0 and 1).
     *
     * @param bossHealth - health of the boss
     */
    public void updateBossHealth(int bossIndex, int bossHealth) {
        if (bossIndex == 1) {
            bossHealthDisplay1.updateProgress((double) bossHealth / BOSS_HEALTH_DIVISOR);
        } else if (bossIndex == 2) {
            bossHealthDisplay2.updateProgress((double) bossHealth / BOSS_HEALTH_DIVISOR);
        }
    }
}