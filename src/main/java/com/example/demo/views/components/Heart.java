package com.example.demo.views.components;

import com.example.demo.utils.AssetPaths;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Objects;

/**
 * View component displaying a heart image.
 */
public class Heart extends ImageView {
    private static final int HEART_HEIGHT = 50;

    /**
     * Constructor to create an instance of Heart.
     */
    public Heart() {
        this.setImage(new Image(Objects.requireNonNull(getClass().getResource(AssetPaths.HEART)).toExternalForm()));
        this.setFitHeight(HEART_HEIGHT);
        this.setPreserveRatio(true);
    }
}