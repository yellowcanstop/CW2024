package com.example.demo.views.components;

import javafx.scene.image.ImageView;
import com.example.demo.assets.*;

/**
 * View component displaying a heart image.
 */
public class Heart extends ImageView {

    private static final int HEART_HEIGHT = 50;

    /**
     * Constructor to create an instance of Heart.
     *
     * @param imageManager - ImageAssetManager to load the heart image
     */
    public Heart(ImageAssetManager imageManager) {
        this.setImage(imageManager.load(AssetPaths.HEART));
        this.setFitHeight(HEART_HEIGHT);
        this.setPreserveRatio(true);
    }
}