package com.example.demo.views;

import javafx.scene.Group;

/**
 * View for level two of the game.
 */
public class LevelTwoView extends LevelView {
	private final Group root;

	/**
	 * Constructor to create an instance of the view for Level Two.
	 *
	 * @param root - group of all nodes to be displayed in the scene
	 * @param heartsToDisplay - the initial number of hearts to be displayed
	 */
	public LevelTwoView(Group root, int heartsToDisplay) {
		super(root, heartsToDisplay);
		this.root = root;
	}
}