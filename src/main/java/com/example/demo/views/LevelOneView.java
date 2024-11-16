package com.example.demo.views;

import javafx.scene.Group;
import javafx.scene.control.Label;

/**
 * View for level one of the game.
 */
public class LevelOneView extends LevelView {
    private final Group root;
    private final Label killCounterLabel;
    private static final int KILL_COUNTER_X_POSITION = 5;
    private static final int KILL_COUNTER_Y_POSITION = 75;
    /**
     * Constructor to create an instance of the view for Level One.
     *
     * @param root - group of all nodes to be displayed in the scene
     * @param heartsToDisplay - the initial number of hearts to be displayed
     */
    public LevelOneView(Group root, int heartsToDisplay) {
        super(root, heartsToDisplay);
        this.root = root;
        this.killCounterLabel = new Label("Kills: 0");
        this.killCounterLabel.setLayoutX(KILL_COUNTER_X_POSITION);
        this.killCounterLabel.setLayoutY(KILL_COUNTER_Y_POSITION);
    }

    /**
     * Show the kill counter.
     */
    public void showKillCounter() {
        root.getChildren().add(killCounterLabel);
    }

    /**
     * Update counter display to show the kills scored.
     *
     * @param killCount - the number of kills
     */
    public void updateCounter(int killCount) {
        killCounterLabel.setText("Kills: " + killCount);
    }
}