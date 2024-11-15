package com.example.demo.controller;

import javafx.stage.Stage;
import javafx.scene.Scene;

/**
 * Interface for the view controller.
 */
public interface ViewController {
    /**
     * Initialize the view controller.
     *
     * @param scene - the scene to set
     * @param stage - the stage to set
     */
    void initialize(Scene scene, Stage stage);

    /**
     * Show the screen.
     */
    void showScreen();
}
