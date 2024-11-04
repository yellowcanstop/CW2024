package com.example.demo.assets;

import com.example.demo.utils.AlertException;
import javafx.scene.media.AudioClip;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// TODO - control volume? - AudioClip.setVolume(double)
// TODO - use Media and MediaPlayer for longer audio clips - more memory-efficient

/**
 * Asset manager for {@code AudioClip} for sound effects in the game.
 * <p>
 * As per JavaFX docs, AudioClip stores in memory the raw, uncompressed audio data
 * for the entire sound. This suits short clips which are played frequently.
 * For background music, the AudioClip will loop continuously until stopped.
 *
 * @see <a href="https://docs.oracle.com/javafx/2/api/javafx/scene/media/AudioClip.html">doc for AudioClip</a>
 */
public class SoundAssetManager extends AssetManager<AudioClip> {

    private final Map<String, AudioClip> sounds = new HashMap<>();

    /**
     * Load an audio clip from the given path.
     *
     * @param path - String name of classpath
     * @return the clip loaded from the path
     */
    @Override
    public AudioClip load(String path) {
        if (sounds.containsKey(path)) {
            return sounds.get(path);
        }
        try {
            AudioClip clip = new AudioClip(Objects.requireNonNull(getClass().getResource(path)).toExternalForm());
            sounds.put(path, clip);
            return clip;
        } catch (Exception e) {
            AlertException.alertException(e);
            return null;
        }
    }

    /**
     * Unload an audio clip from the given path.
     *
     * @param path - String name of classpath
     */
    @Override
    public void unload(String path) {
        try {
            sounds.remove(path);
        } catch (Exception e) {
            AlertException.alertException(e);
        }
    }
}