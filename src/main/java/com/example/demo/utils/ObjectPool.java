package com.example.demo.utils;

import java.util.Stack;

/**
 * Utility class representing an object pool to reuse objects which are frequently created and destroyed during the game.
 * <p>
 * Helps to reduce overhead of object creation and garbage collection since the object is reused.
 * <p>
 * This object pooling is used by the projectiles and the enemies.
 */
public class ObjectPool<T> {
    private final Stack<T> pool;
    private final ObjectFactory<T> factory;

    /**
     * Constructor to create an instance of a stack-based ObjectPool. This is used statically in the Projectile classes.
     *
     * @param factory - an object factory to create new objects.
     */
    public ObjectPool(ObjectFactory<T> factory) {
        this.pool = new Stack<>();
        this.factory = factory;
    }

    /**
     * Get an object from the pool if non-empty, otherwise create a new object.
     *
     * @return an object from the pool or a new object.
     */
    public T get() {
        if (pool.isEmpty()) {
            return factory.create();
        } else {
            return pool.pop();
        }
    }

    /**
     * Release an object back to the pool.
     *
     * @param object - the object to be released.
     */
    public void release(T object) {
        pool.push(object);
    }

    /**
     * Interface to create new objects.
     *
     * @param <T> - the type of object to be created.
     */
    public interface ObjectFactory<T> {
        T create();
    }
}