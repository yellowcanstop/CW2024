package com.example.demo.views;

import com.example.demo.views.components.HeartContainer;
import com.example.demo.views.components.ProgressBarWithLabel;
import javafx.scene.Group;

/**
 * View for level four of the game.
 */
public class LevelFourView extends LevelView {
    private final Group root;
    private final HeartContainer babyHeartContainer;
    private static final int DEFAULT_HEART_HEIGHT = 20;
    private static final int HEART_DISPLAY_X_POSITION = 30;
    private static final int HEART_DISPLAY_Y_POSITION = 90;
    private final ProgressBarWithLabel bossHealthDisplay1;
    private final ProgressBarWithLabel bossHealthDisplay2;
    private static final int BOSS_HEALTH_X_POSITION = 1000;
    private static final int BOSS_HEALTH_Y_POSITION = 50;

    /**
     * Constructor to create an instance of the view for Level Two.
     *
     * @param root - group of all nodes to be displayed in the scene
     * @param heartsToDisplay - the initial number of hearts to be displayed
     */
    public LevelFourView(Group root, int heartsToDisplay, int babyHeartsToDisplay) {
        super(root, heartsToDisplay);
        this.root = root;
        this.babyHeartContainer = new HeartContainer(DEFAULT_HEART_HEIGHT, HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, babyHeartsToDisplay);
        this.bossHealthDisplay1 = new ProgressBarWithLabel(BOSS_HEALTH_X_POSITION, BOSS_HEALTH_Y_POSITION, "Boss Health 1");
        this.bossHealthDisplay2 = new ProgressBarWithLabel(BOSS_HEALTH_X_POSITION, BOSS_HEALTH_Y_POSITION + 50, "Boss Health 2");
    }

    /**
     * Show a container of hearts representing the remaining lives of the player.
     */
    @Override
    public void showHeartDisplay() {
        super.showHeartDisplay();
        root.getChildren().add(babyHeartContainer.getContainer());
    }

    /**
     * Remove hearts from the heart display.
     *
     * @param heartsRemaining - the current number of hearts to be displayed
     */
    public void removeHearts(int heartsRemaining) {
        int heartsToRemove = babyHeartContainer.getCurrentNumberOfHearts() - heartsRemaining;
        for (int i = 0; i < heartsToRemove; i++) {
            babyHeartContainer.removeHeart();
        }
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
    public void updateBossHealth(int bossIndex, int bossHealth, int maxHealth) {
        if (bossIndex == 1) {
            bossHealthDisplay1.updateProgress((double) bossHealth / maxHealth);
        } else if (bossIndex == 2) {
            bossHealthDisplay2.updateProgress((double) bossHealth / maxHealth);
        }
    }
}