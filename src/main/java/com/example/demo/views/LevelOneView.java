package com.example.demo.views;

import javafx.scene.Group;
import com.example.demo.assets.*;

/**
 * View for level one of the game.
 */
public class LevelOneView extends LevelView {

    private final ImageAssetManager imageManager;

    /**
     * Constructor to create an instance of the view for Level One.
     *
     * @param root - group of all nodes to be displayed in the scene
     * @param heartsToDisplay - the initial number of hearts to be displayed
     * @param imageManager - the image manager to load images
     * @param soundManager - the sound manager to load sounds
     */
    public LevelOneView(Group root, int heartsToDisplay, ImageAssetManager imageManager, SoundAssetManager soundManager) {
        super(root, heartsToDisplay, imageManager, soundManager);
        this.imageManager = imageManager;
    }

    /**
     * Unload level-specific resources when the level is no longer needed.
     */
    @Override
    public void unloadResources() {
        imageManager.unload(AssetPaths.BACKGROUND1);
        imageManager.unload(AssetPaths.ENEMY_PLANE);
        imageManager.unload(AssetPaths.ENEMY_FIRE);
    }

}
