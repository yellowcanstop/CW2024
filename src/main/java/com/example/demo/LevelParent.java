package com.example.demo;

import java.util.*;
import java.util.stream.Collectors;
import com.example.demo.controller.InputController;
import com.example.demo.assets.*;
import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.util.Duration;

/**
 * Abstract class which defines the behaviour of a level in the game.
 * <p>
 * Extends {@code Observable} to allow for communication with the Controller class.
 * <p>
 * Contains methods for instantiating a level's scene, handling collisions, and spawning sprites.
 */
public abstract class LevelParent extends Observable {

	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
	private static final int MILLISECOND_DELAY = 50;
	private final double screenHeight;
	private final double screenWidth;
	private final double enemyMaximumYPosition;

	private final Group root;
	private final Timeline timeline;
	private final UserPlane user;
	private final Scene scene;
	private final Background background;

	private final List<ActiveActorDestructible> friendlyUnits;
	private final List<ActiveActorDestructible> enemyUnits;
	private final List<ActiveActorDestructible> userProjectiles;
	private final List<ActiveActorDestructible> enemyProjectiles;
	
	private int currentNumberOfEnemies;
	private final LevelView levelView;
	public final ImageAssetManager imageManager;
	public final SoundAssetManager soundManager;

	/**
	 * Constructor to create an instance of a LevelParent.
	 *
	 * @param backgroundImageName - name of background image for the level
	 * @param screenHeight - height of the screen
	 * @param screenWidth - width of the screen
	 * @param playerInitialHealth - initial number of lives for the player
	 * @param imageManager - the image manager to be used for loading images
	 * @param soundManager - the sound manager to be used for loading sounds
	 */
	public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth, ImageAssetManager imageManager, SoundAssetManager soundManager) {
		this.root = new Group();
		this.scene = new Scene(root, screenWidth, screenHeight);
		this.timeline = new Timeline();
		this.imageManager = imageManager;
		this.soundManager = soundManager;
		this.user = new UserPlane(playerInitialHealth, imageManager);
		this.friendlyUnits = new ArrayList<>();
		this.enemyUnits = new ArrayList<>();
		this.userProjectiles = new ArrayList<>();
		this.enemyProjectiles = new ArrayList<>();
		this.background = new Background(backgroundImageName, imageManager);
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.levelView = instantiateLevelView();
		this.currentNumberOfEnemies = 0;
		initializeTimeline();
		friendlyUnits.add(user);
	}

	/**
	 * Initialize the friendly units for the game level.
	 */
	protected abstract void initializeFriendlyUnits();

	/**
	 * Check if the game is over.
	 */
	protected abstract void checkIfGameOver();

	/**
	 * Spawn the enemy units for the game level.
	 */
	protected abstract void spawnEnemyUnits();

	/**
	 * Instantiate the view for the game level.
	 *
	 * @return the view for the game level
	 */
	protected abstract LevelView instantiateLevelView();

	/**
	 * Initialize the scene displaying the background, friendly units and heart for the game level.
	 *
	 * @return the scene for the game level
	 */
	public Scene initializeScene() {
		initializeBackground();
		initializeFriendlyUnits();
		levelView.showHeartDisplay();
		return scene;
	}

	/**
	 * Start the game level by setting the focus to the background and starting the timeline.
	 * <p>
	 * This method is called in the Controller class.
	 */
	public void startGame() {
		background.requestFocus();
		timeline.play();
	}

	/**
	 * Go to the next level of the game by notifying the Controller class ({@code Observer}).
	 *
	 * @param levelName - name of the next level of the game
	 */
	public void goToNextLevel(String levelName) {
		setChanged();
		notifyObservers(levelName);
	}

	/**
	 * Update the scene by handling collisions and updating sprites, kill count, and level view.
	 */
	private void updateScene() {
		spawnEnemyUnits();
		updateActors();
		generateEnemyFire();
		updateNumberOfEnemies();
		handleEnemyPenetration();
		handleUserProjectileCollisions();
		handleEnemyProjectileCollisions();
		handlePlaneCollisions();
		removeAllDestroyedActors();
		updateKillCount();
		updateLevelView();
		checkIfGameOver();
	}

	/**
	 * Initialize the timeline for the game level.
	 */
	private void initializeTimeline() {
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}

	/**
	 * Initialize the background for the game level.
	 */
	private void initializeBackground() {
		background.setFocusTraversable(true);
		background.setFitHeight(screenHeight);
		background.setFitWidth(screenWidth);
		InputController keyboardController = new InputController(user, instantiateKeyActions());
		background.setOnKeyPressed(keyboardController);
		background.setOnKeyReleased(keyboardController);
		root.getChildren().add(background);
	}

	/**
	 * Instantiate the {@code Map<KeyCode, Runnable>} to define the key actions for the game level.
	 *
	 * @return the {@code Map<KeyCode, Runnable>} containing the key actions for the game level
	 */
	protected Map<KeyCode, Runnable> instantiateKeyActions() {
		Map<KeyCode, Runnable> keyActions = new HashMap<>();
		keyActions.put(KeyCode.SPACE, this::fireProjectile);
		return keyActions;
	}

	/*
	@Override
    protected Map<KeyCode, Runnable> instantiateKeyActions() {
        Map<KeyCode, Runnable> keyActions = super.instantiateKeyActions();
        keyActions.put(KeyCode.M, this::fireBombs);
        return keyActions;
    }

	private void fireBombs()
	*/

	/**
	 * Fire a projectile from the plane controlled by the player.
	 */
	private void fireProjectile() {
		ActiveActorDestructible projectile = user.fireProjectile();
		root.getChildren().add(projectile);
		userProjectiles.add(projectile);
	}

	/**
	 * Spawn projectiles for all enemy units.
	 */
	private void generateEnemyFire() {
		enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
	}

	/**
	 * Display projectile fired by an enemy unit.
	 *
	 * @param projectile - the projectile to be displayed
	 */
	private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
		if (projectile != null) {
			root.getChildren().add(projectile);
			enemyProjectiles.add(projectile);
		}
	}

	/**
	 * Update the actors including friendly units, enemy units, user projectiles, and enemy projectiles.
	 */
	private void updateActors() {
		friendlyUnits.forEach(plane -> plane.updateActor());
		enemyUnits.forEach(enemy -> enemy.updateActor());
		userProjectiles.forEach(projectile -> projectile.updateActor());
		enemyProjectiles.forEach(projectile -> projectile.updateActor());
	}

	/**
	 * Remove all destroyed actors including friendly units, enemy units, user projectiles, and enemy projectiles from the screen.
	 */
	private void removeAllDestroyedActors() {
		removeDestroyedActors(friendlyUnits);
		removeDestroyedActors(enemyUnits);
		removeDestroyedActors(userProjectiles);
		removeDestroyedActors(enemyProjectiles);
	}

	/**
	 * Remove destroyed actors from each list of actors.
	 *
	 * @param actors - list of actors
	 */
	private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
		List<ActiveActorDestructible> destroyedActors = actors.stream().filter(actor -> actor.isDestroyed())
				.toList();
		root.getChildren().removeAll(destroyedActors);
		actors.removeAll(destroyedActors);
	}

	/**
	 * Handle collisions between friendly units and enemy units.
	 */
	private void handlePlaneCollisions() {
		handleCollisions(friendlyUnits, enemyUnits);
	}

	/**
	 * Handle collisions between user projectiles and enemy units.
	 */
	private void handleUserProjectileCollisions() {
		handleCollisions(userProjectiles, enemyUnits);
	}

	/**
	 * Handle collisions between enemy projectiles and friendly units.
	 */
	private void handleEnemyProjectileCollisions() {
		handleCollisions(enemyProjectiles, friendlyUnits);
	}

	/**
	 * Handle collisions between two lists of actors.
	 *
	 * @param actors1 - the first list of actors
	 * @param actors2 - the second list of actors
	 */
	private void handleCollisions(List<ActiveActorDestructible> actors1,
			List<ActiveActorDestructible> actors2) {
		for (ActiveActorDestructible actor : actors2) {
			for (ActiveActorDestructible otherActor : actors1) {
				if (actor.getBoundsInParent().intersects(otherActor.getBoundsInParent())) {
					actor.takeDamage();
					otherActor.takeDamage();
				}
			}
		}
	}

	/**
	 * Handle enemy units which have moved past the player by adding damage to the player and removing the enemy unit.
	 */
	private void handleEnemyPenetration() {
		for (ActiveActorDestructible enemy : enemyUnits) {
			if (enemyHasPenetratedDefenses(enemy)) {
				user.takeDamage();
				enemy.destroy();
			}
		}
	}

	/**
	 * Update the level view by removing hearts from the display based on the player's health.
	 */
	private void updateLevelView() {
		levelView.removeHearts(user.getHealth());
	}

	/**
	 * Increment the kill count for each enemy unit which has been destroyed.
	 */
	private void updateKillCount() {
		for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
			user.incrementKillCount();
		}
	}

	/**
	 * Check if an enemy unit has moved past the player.
	 *
	 * @param enemy - enemy unit whose position on the screen is being checked
	 * @return true if enemy has moved past the player sprite, else false
	 */
	private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
		return Math.abs(enemy.getTranslateX()) > screenWidth;
	}

	/**
	 * Indicate a game win by stopping the timeline and displaying a win image.
	 */
	protected void winGame() {
		timeline.stop();
		levelView.showWinImage();
	}

	/**
	 * Indicate a game loss by stopping the timeline and displaying a game over image.
	 */
	protected void loseGame() {
		timeline.stop();
		levelView.showGameOverImage();
	}

	/**
	 * Unload resources for the game level.
	 */
	public void unloadResources() {
		// unload other resources like scene?
		background.unloadResources();
		levelView.unloadResources();
	}

	/**
	 * Get the user plane controlled by the player.
	 *
	 * @return the UserPlane object controlled by the player
	 */
	protected UserPlane getUser() {
		return user;
	}

	/**
	 * Get the root group containing all nodes displayed in the game level.
	 *
	 * @return the Group object containing all nodes displayed in the game level
	 */
	protected Group getRoot() {
		return root;
	}

	/**
	 * Get the current number of enemies on the screen.
	 *
	 * @return the size of the list of enemy units
	 */
	protected int getCurrentNumberOfEnemies() {
		return enemyUnits.size();
	}

	/**
	 * Add an enemy unit to the game level.
	 *
	 * @param enemy - enemy unit to be added to the game level
	 */
	protected void addEnemyUnit(ActiveActorDestructible enemy) {
		enemyUnits.add(enemy);
		root.getChildren().add(enemy);
	}

	/**
	 * Get the maximum y position for an enemy unit.
	 *
	 * @return the maximum y position for an enemy unit
	 */
	protected double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}

	/**
	 * Get the width of the screen.
	 *
	 * @return the width of the screen
	 */
	protected double getScreenWidth() {
		return screenWidth;
	}

	/**
	 * Check if the user plane has been destroyed.
	 *
	 * @return true if the user plane has been destroyed, else false
	 */
	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}

	/**
	 * Update the currentNumberOfEnemies to the current size of the list of enemy units.
	 */
	private void updateNumberOfEnemies() {
		currentNumberOfEnemies = enemyUnits.size();
	}

}
