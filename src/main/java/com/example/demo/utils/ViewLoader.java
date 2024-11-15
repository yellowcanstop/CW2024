package com.example.demo.utils;

import com.example.demo.controller.ViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Utility class to load a screen in the game.
 */
public class ViewLoader {
    public static final int SCREEN_WIDTH = 1300;
    public static final int SCREEN_HEIGHT = 750;
    private static final String TITLE = "Sky Battle";

    /**
     * Load the view by setting up a FXMLLoader, Scene, ViewController and configuring the view.
     *
     * @param stage - the stage to display the view
     * @param path - the path to the FXML file
     * @return a configured controller of the view
     * @throws IOException if the FXML file is not found
     */
    public static ViewController loadView(Stage stage, String path) throws IOException {
        FXMLLoader loader = new FXMLLoader(ViewLoader.class.getResource(path));
        Scene scene = new Scene(loader.load());
        ViewController controller = loader.getController();
        controller.initialize(scene, stage);
        configureView(stage);
        return controller;
    }

    /**
     * Configure the view by setting the title, size and making it non-resizable.
     */
    private static void configureView(Stage stage) {
        stage.setTitle(TITLE);
        stage.setResizable(false);
        stage.setHeight(SCREEN_HEIGHT);
        stage.setWidth(SCREEN_WIDTH);
    }
}