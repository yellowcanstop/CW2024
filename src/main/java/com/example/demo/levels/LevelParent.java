package com.example.demo.levels;

import java.util.*;
import com.example.demo.models.Plane;
import com.example.demo.utils.CollisionHandler;
import com.example.demo.utils.FireAction;
import com.example.demo.utils.MusicLoader;
import com.example.demo.utils.SoundLoader;
import com.example.demo.models.DestructibleSprite;
import com.example.demo.models.UserPlane;
import com.example.demo.controller.InputController;
import com.example.demo.views.LevelView;
import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.util.Duration;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Abstract class which defines the behaviour of a level in the game.
 * <p>
 * Uses a StringProperty for the GameController ({@code InvalidationListener}) to observe for changes in the level name.
 * <p>
 * Contains methods for instantiating a level's scene, handling collisions, and spawning sprites.
 */
public abstract class LevelParent {
	private final StringProperty levelNameProperty;
	private final Group root;
	private final Timeline timeline;
	private static final int MILLISECOND_DELAY = 50;
	private final Scene scene;
	private final double screenHeight;
	private final double screenWidth;
	private final ImageView background;
	private final MusicLoader musicLoader;
	private final UserPlane user;
	private final CollisionHandler collisionHandler;
	private final List<DestructibleSprite> friendlyUnits;
	private final List<DestructibleSprite> enemyUnits;
	private final List<DestructibleSprite> userProjectiles;
	private final List<DestructibleSprite> enemyProjectiles;
	private final double enemyMaximumYPosition;
	private static final double SCREEN_HEIGHT_ADJUSTMENT = 250;
	private static final long FIRE_COOL_DOWN = 500;
	public static final String FIRE_PROJECTILE = "/com/example/demo/sounds/laser_shot2.wav";
	private int currentNumberOfEnemies;

	/**
	 * Constructor to create an instance of a LevelParent.
	 *
	 * @param backgroundImageName - name of background image for the level
	 * @param musicName - name of music for the level
	 * @param screenHeight - height of the screen
	 * @param screenWidth - width of the screen
	 * @param playerInitialHealth - initial number of lives for the player
	 */
	public LevelParent(String backgroundImageName, String musicName, double screenHeight, double screenWidth, int playerInitialHealth) {
		this.levelNameProperty = new SimpleStringProperty();
		this.root = new Group();
		this.scene = new Scene(root, screenWidth, screenHeight);
		this.timeline = new Timeline();
		this.background = new ImageView();
		background.setImage(new Image(Objects.requireNonNull(getClass().getResource(backgroundImageName)).toExternalForm()));
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.musicLoader = new MusicLoader(musicName);
		this.user = new UserPlane(playerInitialHealth);
		this.friendlyUnits = new ArrayList<>();
		this.enemyUnits = new ArrayList<>();
		this.userProjectiles = new ArrayList<>();
		this.enemyProjectiles = new ArrayList<>();
		this.collisionHandler = new CollisionHandler();
		this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.currentNumberOfEnemies = 0;
		initializeTimeline();
		musicLoader.playMusic();
		friendlyUnits.add(user);
	}

	/**
	 * Initialize the scene composed of background, units, and view for the game level.
	 *
	 * @return the scene for the game level
	 */
	public Scene initializeScene() {
		initializeBackground();
		initializeUnits();
		initializeLevelView();
		return scene;
	}

	/**
	 * Start the game level by setting the focus to the background and starting the timeline.
	 */
	public void startGame() {
		background.requestFocus();
		timeline.play();
	}

	/**
	 * Go to the next level of the game by setting the level name property (which is observed by the GameController).
	 *
	 * @param levelName - name of the next level of the game
	 */
	public void goToNextLevel(String levelName) {
		timeline.stop();
		musicLoader.stopMusic();
		levelNameProperty.set(levelName);
	}

	/**
	 * Get the level name property.
	 *
	 * @return the level name property
	 */
	public StringProperty getLevelNameProperty() {
		return levelNameProperty;
	}

	/**
	 * Check if the game is over.
	 */
	protected abstract void checkIfGameOver();

	/**
	 * Spawn the enemy units for the game level.
	 */
	protected abstract void spawnEnemyUnits();

	/**
	 * Get the view for the game level.
	 *
	 * @return the view for the game level
	 */
	protected abstract LevelView getLevelView();

	/**
	 * Initialize the level view for the game level.
	 */
	protected void initializeLevelView() {
		getLevelView().showHeartDisplay();
	}

	/**
	 * Update the level view by removing hearts from the display based on the player's health.
	 */
	protected void updateLevelView() {
		getLevelView().removeHearts(user.getHealth());
	}

	/**
	 * Initialize the units for the game level.
	 */
	protected void initializeUnits() {
		root.getChildren().add(user);
	}

	/**
	 * Indicate a game win by stopping the timeline and displaying a win image.
	 */
	protected void winGame() {
		timeline.stop();
		musicLoader.stopMusic();
		getLevelView().showWinImage();
	}

	/**
	 * Indicate a game loss by stopping the timeline and displaying a game over image.
	 */
	protected void loseGame() {
		timeline.stop();
		musicLoader.stopMusic();
		getLevelView().showGameOverImage();
	}

	/**
	 * Add an enemy unit to the game level.
	 *
	 * @param enemy - enemy unit to be added to the game level
	 */
	protected void addEnemyUnit(DestructibleSprite enemy) {
		enemyUnits.add(enemy);
		root.getChildren().add(enemy);
	}

	/**
	 * Add a friendly unit to the game level.
	 *
	 * @param friend - friendly unit to be added to the game level
	 */
	protected void addFriendlyUnit(DestructibleSprite friend) {
		friendlyUnits.add(friend);
		root.getChildren().add(friend);
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
	 * Update the scene by spawning and cleaning up sprites, handle collisions, update view, check state of level.
	 */
	private void updateScene() {
		spawnEnemyUnits();
		updateActors();
		generateEnemyFire();
		updateNumberOfEnemies();
		handleEnemyPenetration();
		handleOffScreen();
		handleUserProjectileCollisions();
		handleEnemyProjectileCollisions();
		handlePlaneCollisions();
		removeAllDestroyedActors();
		updateKillCount();
		updateLevelView();
		checkIfGameOver();
	}

	/**
	 * Initialize the background and input key actions for the game level.
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
		keyActions.put(KeyCode.SPACE, new FireAction(getRoot(), getUserProjectiles(), new SoundLoader(FIRE_PROJECTILE), FIRE_COOL_DOWN, getUser()::fireBullet));
		return keyActions;
	}

	/**
	 * Spawn projectiles for all enemy units.
	 */
	private void generateEnemyFire() {
		enemyUnits.forEach(enemy -> spawnEnemyProjectile(((Plane) enemy).fireBullet()));
	}

	/**
	 * Display projectile fired by an enemy unit.
	 *
	 * @param projectile - the projectile to be displayed
	 */
	private void spawnEnemyProjectile(DestructibleSprite projectile) {
		if (projectile != null) {
			root.getChildren().add(projectile);
			enemyProjectiles.add(projectile);
		}
	}

	/**
	 * Update the actors including friendly units, enemy units, user projectiles, and enemy projectiles.
	 */
	private void updateActors() {
		friendlyUnits.forEach(DestructibleSprite::updateActor);
		enemyUnits.forEach(DestructibleSprite::updateActor);
		userProjectiles.forEach(DestructibleSprite::updateActor);
		enemyProjectiles.forEach(DestructibleSprite::updateActor);
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
	private void removeDestroyedActors(List<DestructibleSprite> actors) {
		List<DestructibleSprite> destroyedActors = actors.stream().filter(DestructibleSprite::isDestroyed)
				.toList();
		root.getChildren().removeAll(destroyedActors);
		actors.removeAll(destroyedActors);
	}

	/**
	 * Handle collisions between friendly units and enemy units.
	 */
	private void handlePlaneCollisions() {
		collisionHandler.handleCollisions(friendlyUnits, enemyUnits);
	}

	/**
	 * Handle collisions between user projectiles and enemy units.
	 */
	private void handleUserProjectileCollisions() {
		collisionHandler.handleCollisions(userProjectiles, enemyUnits);
	}

	/**
	 * Handle collisions between enemy projectiles and friendly units.
	 */
	private void handleEnemyProjectileCollisions() {
		collisionHandler.handleCollisions(enemyProjectiles, friendlyUnits);
	}

	/**
	 * Handle enemy units which have moved off the screen by adding damage to the player and removing the enemy unit.
	 */
	private void handleEnemyPenetration() {
		for (DestructibleSprite enemy : enemyUnits) {
			if (checkOffScreen(enemy)) {
				user.takeDamage();
				enemy.destroy();
			}
		}
	}

	/**
	 * Handle projectiles which have moved off the screen by destroying them.
	 */
	private void handleOffScreen() {
		for (DestructibleSprite enemyProjectile : enemyProjectiles) {
			if (checkOffScreen(enemyProjectile)) {
				enemyProjectile.destroy();
			}
		}
	}

	/**
	 * Check if a destructible has moved off the screen.
	 *
	 * @param destructible - destructible whose position on the screen is being checked
	 * @return true if destructible has moved off the screen, else false
	 */
	private boolean checkOffScreen(DestructibleSprite destructible) {
		return Math.abs(destructible.getTranslateX()) > screenWidth;
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
	 * Get the list of user projectiles.
	 *
	 * @return the list of user projectiles
	 */
	protected List<DestructibleSprite> getUserProjectiles() {
		return userProjectiles;
	}

	/**
	 * Update the currentNumberOfEnemies to the current size of the list of enemy units.
	 */
	private void updateNumberOfEnemies() {
		currentNumberOfEnemies = enemyUnits.size();
	}
}