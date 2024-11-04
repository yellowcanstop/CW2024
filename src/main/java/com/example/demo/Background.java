package com.example.demo;

import javafx.scene.image.ImageView;
import com.example.demo.assets.*;

/**
 * View component displaying a background image.
 */
public class Background extends ImageView {

    private final ImageAssetManager imageManager;
    private final String backgroundImageName;

    /**
     * Constructor to create an instance of Background.
     *
     * @param imageManager - ImageAssetManager to load the background image
     */
    public Background(String backgroundImageName, ImageAssetManager imageManager) {
        this.backgroundImageName = backgroundImageName;
        this.imageManager = imageManager;
        this.setImage(imageManager.load(backgroundImageName));
    }

    public void unloadResources() {
        imageManager.unload(backgroundImageName);
    }
}