package com.example.demo.views.components;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Objects;

/**
 * View component displaying a heart image.
 */
public class Heart extends ImageView {
    private static final int HEART_HEIGHT = 50;
    public static final String HEART = "/com/example/demo/images/heart.png";

    /**
     * Constructor to create an instance of Heart.
     */
    public Heart() {
        this.setImage(new Image(Objects.requireNonNull(getClass().getResource(HEART)).toExternalForm()));
        this.setFitHeight(HEART_HEIGHT);
        this.setPreserveRatio(true);
    }
}