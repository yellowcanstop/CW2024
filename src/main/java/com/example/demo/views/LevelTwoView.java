package com.example.demo.views;

import javafx.scene.Group;
import javafx.scene.control.Label;

/**
 * View for level two of the game.
 */
public class LevelTwoView extends LevelView {
	private final Group root;
	private final Label bossHealthLabel;
	private static final int BOSS_HEALTH_X_POSITION = 1000;
	private static final int BOSS_HEALTH_Y_POSITION = 20;

	/**
	 * Constructor to create an instance of the view for Level Two.
	 *
	 * @param root - group of all nodes to be displayed in the scene
	 * @param heartsToDisplay - the initial number of hearts to be displayed
	 */
	public LevelTwoView(Group root, int heartsToDisplay) {
		super(root, heartsToDisplay);
		this.root = root;
		this.bossHealthLabel = new Label("Boss Health: ");
		this.bossHealthLabel.setLayoutX(BOSS_HEALTH_X_POSITION);
		this.bossHealthLabel.setLayoutY(BOSS_HEALTH_Y_POSITION);
	}

	/**
	 * Show the boss health.
	 */
	public Label getBossHealthLabel() {
		return bossHealthLabel;
	}

	/**
	 * Update display to show the boss health.
	 *
	 * @param bossHealth - health of the boss
	 */
	public void updateBossHealth(int bossHealth) {
		bossHealthLabel.setText("Boss Health: " + bossHealth);
	}
}