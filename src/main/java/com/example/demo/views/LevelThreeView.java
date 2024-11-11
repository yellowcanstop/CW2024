package com.example.demo.views;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

/**
 * View for level three of the game.
 */
public class LevelThreeView extends LevelView {
    private final Group root;
    private final Label bossHealthLabel1;
    private final Label bossHealthLabel2;
    private final ProgressBar bossHealthDisplay1;
    private final ProgressBar bossHealthDisplay2;
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
        this.bossHealthLabel1 = new Label("Boss 1 Health");
        this.bossHealthLabel1.setLayoutX(BOSS_HEALTH_X_POSITION);
        this.bossHealthLabel1.setLayoutY(BOSS_HEALTH_Y_POSITION - 20);
        this.bossHealthDisplay1 = new ProgressBar();
        this.bossHealthDisplay1.setLayoutX(BOSS_HEALTH_X_POSITION);
        this.bossHealthDisplay1.setLayoutY(BOSS_HEALTH_Y_POSITION);
        this.bossHealthDisplay1.setPrefWidth(200);
        this.bossHealthDisplay1.setProgress(1.0);
        this.bossHealthLabel2 = new Label("Boss 2 Health");
        this.bossHealthLabel2.setLayoutX(BOSS_HEALTH_X_POSITION);
        this.bossHealthLabel2.setLayoutY(BOSS_HEALTH_Y_POSITION + 40);
        this.bossHealthDisplay2 = new ProgressBar();
        this.bossHealthDisplay2.setLayoutX(BOSS_HEALTH_X_POSITION);
        this.bossHealthDisplay2.setLayoutY(BOSS_HEALTH_Y_POSITION + 50);
        this.bossHealthDisplay2.setPrefWidth(200);
        this.bossHealthDisplay2.setProgress(1.0);
    }

    /**
     * Get the boss health label.
     */
    public Label getBossHealthLabel1() {
        return bossHealthLabel1;
    }

    public Label getBossHealthLabel2() {
        return bossHealthLabel2;
    }

    /**
     * Get the boss health display.
     */
    public ProgressBar getBossHealthDisplay1() {
        return bossHealthDisplay1;
    }

    public ProgressBar getBossHealthDisplay2() {
        return bossHealthDisplay2;
    }

    /**
     * Update display to show the boss health (percentage between 0 and 1).
     *
     * @param bossHealth - health of the boss
     */
    public void updateBossHealth1(int bossHealth) {
        bossHealthDisplay1.setProgress((double) bossHealth / 10);
    }

    public void updateBossHealth2(int bossHealth) {
        bossHealthDisplay2.setProgress((double) bossHealth / 10);
    }
}