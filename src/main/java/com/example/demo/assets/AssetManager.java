package com.example.demo.assets;

/*
 * Abstract class to manage assets such as images and sounds for the game.
 * <p>
 * Explicitly unload resources to immediately free up memory since there is no guarantee when the Java garbage collector will run.
 * This helps track resource usage and maintain a predictable memory usage pattern.
 */
public abstract class AssetManager<T> {
    /**
     * Load an asset from the given path.
     *
     * @param path - String name of classpath
     * @return the asset of generic type T
     */
    public abstract T load(String path);

    /**
     * Unload an asset from the given path.
     *
     * @param path - String name of classpath
     */
    public abstract void unload(String path);
}