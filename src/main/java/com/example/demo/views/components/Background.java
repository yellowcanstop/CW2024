package com.example.demo.views.components;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Objects;

/**
 * View component displaying a background image.
 */
public class Background extends ImageView {
    private final String backgroundImageName;

    /**
     * Constructor to create an instance of Background.
     *
     * @param backgroundImageName - the String classpath of the image for the background
     */
    public Background(String backgroundImageName) {
        this.backgroundImageName = backgroundImageName;
        this.setImage(new Image(Objects.requireNonNull(getClass().getResource(backgroundImageName)).toExternalForm()));
    }
}