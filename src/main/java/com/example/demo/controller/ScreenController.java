package com.example.demo.controller;

import javafx.stage.Stage;
import javafx.scene.Scene;

/**
 * Interface for the view controller.
 */
public interface ScreenController {
    /**
     * Initialize the view controller.
     *
     * @param scene - the scene to set
     * @param stage - the stage to set
     * @throws Exception - if the view controller cannot be initialized
     */
    void initialize(Scene scene, Stage stage) throws Exception;

    /**
     * Show the screen.
     */
    void showScreen();
}