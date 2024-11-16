package com.example.demo.views;

import com.example.demo.views.components.ProgressBarWithLabel;
import javafx.scene.Group;

/**
 * View for level two of the game.
 */
public class LevelTwoView extends LevelView {
	private final Group root;
	private final ProgressBarWithLabel bossHealthDisplay;
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
		this.bossHealthDisplay = new ProgressBarWithLabel(BOSS_HEALTH_X_POSITION, BOSS_HEALTH_Y_POSITION, "Boss Health");
	}

	/**
	 * Show the boss health display.
	 */
	public void showBossHealthDisplay() {
		root.getChildren().add(bossHealthDisplay.getProgressLabel());
		root.getChildren().add(bossHealthDisplay.getProgressBar());
	}

	/**
	 * Update display to show the boss health (percentage between 0 and 1).
	 *
	 * @param bossHealth - health of the boss
	 */
	public void updateBossHealth(int bossHealth, int maxHealth) {
		bossHealthDisplay.updateProgress((double) bossHealth / maxHealth);
	}
}