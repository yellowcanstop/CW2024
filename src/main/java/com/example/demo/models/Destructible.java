package com.example.demo.models;

/**
 * Interface for objects which can take damage and be destroyed in the game.
 */
public interface Destructible {
	/**
	 * Handle damage taken by the destructible.
	 */
	void takeDamage();

	/**
	 * Destroy the destructible.
	 */
	void destroy();

	/**
	 * Check if the destructible is destroyed.
	 *
	 * @return true if destructible is destroyed, else false
	 */
	boolean isDestroyed();

	/**
	 * Reset the isDestroyed attribute to false.
	 */
	default void resetIsDestroyed() {}
}