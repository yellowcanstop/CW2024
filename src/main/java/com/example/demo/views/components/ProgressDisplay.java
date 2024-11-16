package com.example.demo.views.components;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

/**
 * View component representing a progress bar with a label.
 */
public class ProgressDisplay {
    private final Label progressLabel;
    private final ProgressBar progressBar;
    private static final int LABEL_BAR_OFFSET = 20;

    /**
     * Constructor to create an instance of ProgressDisplay.
     *
     * @param xPosition - the x position of the progress bar
     * @param yPosition - the y position of the progress bar
     * @param labelText - the text to display on the label
     */
    public ProgressDisplay(double xPosition, double yPosition, String labelText) {
        this.progressLabel = new Label(labelText);
        progressLabel.setLayoutX(xPosition);
        progressLabel.setLayoutY(yPosition - LABEL_BAR_OFFSET);
        this.progressBar = new ProgressBar();
        this.progressBar.setLayoutX(xPosition);
        this.progressBar.setLayoutY(yPosition);
        this.progressBar.setPrefWidth(200);
        this.progressBar.setProgress(1.0);
    }

    /**
     * Update the progress of the progress bar.
     *
     * @param progress - the progress value between 0 and 1
     */
    public void updateProgress(double progress) {
        progressBar.setProgress(progress);
    }

    /**
     * Get the progress bar.
     *
     * @return the progress bar
     */
    public ProgressBar getProgressBar() {
        return progressBar;
    }

    /**
     * Get the progress label.
     *
     * @return the progress label
     */
    public Label getProgressLabel() {
        return progressLabel;
    }
}