package com.example.demo.assets;

import com.example.demo.controller.AlertUtils;
import javafx.scene.image.Image;
import java.util.HashMap;
import java.util.Map;

/**
 * Asset manager for {@code Image} for images in the game.
 *
 * @see <a href="https://docs.oracle.com/javase/8/javafx/api/javafx/scene/image/Image.html">doc for Image</a>
 */
public class ImageAssetManager extends AssetManager<Image> {

    private Map<String, Image> images = new HashMap<>();

    /**
     * Load an image from the given path.
     *
     * @param path - String name of classpath
     * @return the image loaded from the path, else null
     */
    @Override
    public Image load(String path) {
        if (images.containsKey(path)) {
            return images.get(path);
        }
        try {
            Image image = new Image(path);
            images.put(path, image);
            return image;
        } catch (Exception e) {
            AlertUtils.alertException(e);
            return null;
        }
    }

    /**
     * Unload an image from the given path.
     *
     * @param path - String name of classpath
     */
    @Override
    public void unload(String path) {
        try {
            images.remove(path);
        } catch (Exception e) {
            AlertUtils.alertException(e);
        }
    }
}
