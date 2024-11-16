package com.example.demo.models;

/**
 * Interface for objects that can be released back to a pool.
 */
public interface Poolable {
    /**
     * Release the object back to the pool.
     */
    void release();

    /**
     * Reset the state of the object.
     */
    void reset();
}