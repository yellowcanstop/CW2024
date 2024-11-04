package com.example.demo.models;

/**
 * Interface for sprites which can take damage and be destroyed in the game.
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
	
}
