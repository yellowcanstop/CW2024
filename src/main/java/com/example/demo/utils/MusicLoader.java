package com.example.demo.utils;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.util.Objects;

/**
 * Utility class to load background music for the game.
 * <p>
 * Implemented using MediaPlayer from JavaFX which is more memory-efficient for large audio files vs AudioClip.
 */
public class MusicLoader {
    private final MediaPlayer mediaPlayer;

    /**
     * Set the media to play music.
     *
     * @param path - the path of the music file
     */
    public MusicLoader(String path) {
        Media media = new Media(Objects.requireNonNull(getClass().getResource(path)).toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    /**
     * Play the music.
     */
    public void playMusic() {
        try {
            mediaPlayer.play();
        } catch (NullPointerException e) {
            AlertException.alertException(e);
        }
    }

    /**
     * Stop the music.
     */
    public void stopMusic() {
        try {
            mediaPlayer.stop();
        } catch (NullPointerException e) {
            AlertException.alertException(e);
        }
    }
}