package com.example.demo.models;

public interface InputControlledObject {
    default void moveUp() {};
    default void moveDown() {};
    default void moveLeft() {};
    default void moveRight() {};
    default void stop() {};
}
