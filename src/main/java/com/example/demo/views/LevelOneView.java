package com.example.demo.views;

import javafx.scene.Group;

/**
 * View for level one of the game.
 */
public class LevelOneView extends LevelView {
    /**
     * Constructor to create an instance of the view for Level One.
     *
     * @param root - group of all nodes to be displayed in the scene
     * @param heartsToDisplay - the initial number of hearts to be displayed
     */
    public LevelOneView(Group root, int heartsToDisplay) {
        super(root, heartsToDisplay);
    }
}