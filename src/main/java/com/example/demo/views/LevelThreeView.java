package com.example.demo.views;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

/**
 * View for level three of the game.
 */
public class LevelThreeView extends LevelView {
    private final Group root;
    private final Label bossHealthLabel;
    private final ProgressBar bossHealthDisplay;
    private static final int BOSS_HEALTH_X_POSITION = 1000;
    private static final int BOSS_HEALTH_Y_POSITION = 20;

    /**
     * Constructor to create an instance of the view for Level Two.
     *
     * @param root - group of all nodes to be displayed in the scene
     * @param heartsToDisplay - the initial number of hearts to be displayed
     */
    public LevelThreeView(Group root, int heartsToDisplay) {
        super(root, heartsToDisplay);
        this.root = root;
        this.bossHealthLabel = new Label("Boss Health");
        this.bossHealthLabel.setLayoutX(BOSS_HEALTH_X_POSITION);
        this.bossHealthLabel.setLayoutY(BOSS_HEALTH_Y_POSITION - 20);
        this.bossHealthDisplay = new ProgressBar();
        this.bossHealthDisplay.setLayoutX(BOSS_HEALTH_X_POSITION);
        this.bossHealthDisplay.setLayoutY(BOSS_HEALTH_Y_POSITION);
        this.bossHealthDisplay.setPrefWidth(200);
        this.bossHealthDisplay.setProgress(1.0);
        this.bossHealthDisplay.getStyleClass().add("boss-progress-bar");
    }

    /**
     * Get the boss health label.
     */
    public Label getBossHealthLabel() {
        return bossHealthLabel;
    }

    /**
     * Get the boss health display.
     */
    public ProgressBar getBossHealthDisplay() {
        return bossHealthDisplay;
    }

    /**
     * Update display to show the boss health (percentage between 0 and 1).
     *
     * @param bossHealth - health of the boss
     */
    public void updateBossHealth(int bossHealth) {
        bossHealthDisplay.setProgress((double) bossHealth / 10);
    }
}