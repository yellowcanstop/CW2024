package com.example.demo.utils;

import javafx.scene.media.AudioClip;
import java.util.Objects;

/**
 * Utility class to load sound effects for the game.
 * <p>
 * Implemented using AudioClip from JavaFX which is suitable for short audio clips.
 */
public class SoundLoader {
    private final AudioClip sound;

    /**
     * Set the media to play sound.
     *
     * @param path - the path of the sound file
     */
    public SoundLoader(String path) {
        this.sound = new AudioClip(Objects.requireNonNull(getClass().getResource(path)).toExternalForm());
    }

    /**
     * Play the sound.
     */
    public void playSound() {
        try {
            sound.play();
        } catch (NullPointerException e) {
            AlertException.alertException(e);
        }
    }
}
