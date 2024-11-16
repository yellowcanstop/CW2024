package com.example.demo.models;

/**
 * Interface for objects that can be controlled by user input.
 */
public interface InputControlledObject {
    /**
     * Move the object up.
     */
    default void moveUp() {};

    /**
     * Move the object down.
     */
    default void moveDown() {};

    /**
     * Move the object left.
     */
    default void moveLeft() {};

    /**
     * Move the object right.
     */
    default void moveRight() {};

    /**
     * Stop the movement.
     */
    default void stop() {};
}
