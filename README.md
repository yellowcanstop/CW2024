# COMP2042
## Academic Year 2024/25: Maintenance and Extension of the game "Sky Battle"

### GitHub
[Link to GitHub Repository](https://github.com/yellowcanstop/CW2024)

### Compilation Instructions
1.  **Verify Java installation and version:**  
    Ensure Java version 17 is installed.
```sh  
java -version  
```  
2. **Clone the repository and navigate to the directory containing the pom.xml file:**
```sh  
git clone https://github.com/yellowcanstop/CW2024  
cd CW2024  
```  
3. **Open the project in your IDE of choice to check if the dependencies in the pom.xml file are installed/updated:**
- Ensure JavaFX SDK is installed.
- Other dependencies (e.g. JUnit, Mockito) are already included in the pom.xml file which can be parsed and loaded in an IDE, for example IntelliJ IDEA.
4.  **Compile the project using the Maven Wrapper:**
```sh  
mvnw.cmd clean compile     // Windows  
./mvnw clean compile       // Unix-based systems  
```  
5.  **Run the application using the Maven Wrapper:**
```sh  
mvnw.cmd javafx:run       // Windows  
./mvnw javafx:run         // Unix-based systems  
```  
6. **Run tests inside the IDE (IntelliJ IDEA):**
- In Project Structure, right-click on *"CW2024\src\test\java\com\example\demo"*
- Select the option *"Run Tests in 'demo'"* to run all 21 tests.

### Implemented and Working Properly

This section is divided into:
1. Bug Fixes
2. Refactoring
3. Gameplay Enhancement

#### 1) Bug Fixes

1. **Missing removal of observable when transitioning levels**
- **Problem:** Slow loading of game assets when Level 2 starts after winning Level 1. Occasional OutOfMemoryError.
- **Fix:** Delete the observable of the previous level (Level 1) before transitioning to the next level (Level 2) in GameController (the observer).
> Note: The implementation of the Observer design pattern is later replaced by InvalidationListener (see: Refactoring). The equivalent of deleteObserver() is removeListener().

2. **Missing reset of timeline when transitioning levels**
- This subtle bug is separate from Bug 1 above.  It was only detected after adding a sound effect when the GameOverDisplay is shown - the "GameOverSound". After winning Level 1, the transition to Level 2 is smooth: all game assets load quickly and gameplay proceeds as usual.
- **Problem:** It is in the middle of gameplay for Level 2 that the user can hear the "GameOverSound" played once.
- **Steps taken:** Logging to console to track when every method which plays the "GameOverSound" is called. Traced that Level 1's updateScene() and hence checkIfGameOver() were still being called in the middle of Level 2 gameplay, triggering the "GameOverSound".
- **Fix:** Add timeline.stop() before calling setchanged() and notifyobservers() in the method goToNextLevel() in LevelParent.
> Note: The implementation of the Observer design pattern is later replaced by InvalidationListener (see: Refactoring). The equivalent is to add timeline.stop() before levelNameProperty.set() in the method goToNextLevel() in LevelParent.

3. **Fix shield visibility**
- **Problem:** Shield does not show in Level 2. When shield probability is changed to 1 temporarily, the BossPlane is shielded (health is not decremented when colliding with bullet) but the shield is not visible.
- **Fix:** This problem could arise due to how each level is constructed via reflection in GameController and cast into LevelParent instead of being directly called. The logic of initializing a level view (LevelView) was previously in the superclass LevelParent. To ensure that specific level views are called instead of LevelView, each level should initialize their respective views in their constructors. These views are returned by their instantiateLevelView() methods.
- **Addition:** Bind shield position properties to BossPlane so shield moves together with BossPlane as a single entity.

4. **Typo in Shield image file path**
- **Problem:** Incorrect file extension (png replaced with jpg) for path of shield image file. NullPointerException.
- **Fix:** Correct typo in shield image file path. Add utility class AlertException for graceful handling of exceptions and termination of application.


#### 2) Refactoring

1. **Object Pooling mechanism (See: memory_analysis.pdf for screenshots)**
- **Why?** Optimize memory usage since BossBullets and EnemyBullets are frequently created and destroyed throughout gameplay. BossBullets are reused across game levels (Level 2, 3, 4) across game sessions (win/lose -> play again) in a single application launch. EnemyBullets are reused throughout the game Level 1 in an individual game session.
- **Is there a memory benefit?** A separate "nopool" branch without feature 2.1 and 2.2 *(checking off-screen bounds)* was created. The results of memory usage are in memory_analysis.pdf, also summarized below:
- BossBullets used only 13.5% (179MB) of memory vs 23.8% (688MB).
- EnemyBullets used only 17.0% (225MB) of memory vs 25.2% (727MB).
- Call tree memory allocation for the game was at 1.31GB vs 2.86GB.
- Memory usage stayed at 550-600MB range after 6-8 game sessions in a single application launch, vs 630-680MB range.
> **Implementation:** BossBullet and EnemyBullet implement the Poolable interface.For each poolable, construct a static object pool (which is a Stack<T>). To get an instance of the poolable, its public static create() method is called (and its original constructor is set to private).  In the create() method: if the pool is empty, a fresh instance of the poolable is created using ObjectFactory<T> (an inner interface with create() inside ObjectPool). If the pool is non-empty, an existing instance is retrieved with a fresh state (Boolean isDestroyed set to false).  When an instance is destroyed in the game *(see: Destroy unneeded instances)*, it is released back into the pool for future reuse.

2. **Destroy unneeded instances by checking off-screen bounds**
- Add handleOffScreen() method in updateScene() in LevelParent. When a projectile or EnemyPlane moves off the screen, it is destroyed and released back to the pool if the object is a poolable.

3. **Update libraries in implementation of Observer design pattern**
- **Problem:** java.util.Observer for Controller and java.util.Observable for LevelParent are deprecated in Java 9.
- **Fix:** This is refactored to use javafx.beans.InvalidationListener (instead of Observer) and Observable from javafx.beans. LevelParent uses a javafx.beans.property.StringProperty to be observed by the InvalidationListener to watch for changes in the level name.
> **Reason:** InvalidationListener is chosen over ChangeListener since the former offers a simpler API (invalidated method only takes an Observable parameter). The old value of the observable property returned by ChangeListener is also not too meaningful in this case of a level switch.

4.  **Dependency Inversion by relying on interfaces instead of concrete classes**
- **ScreenController:** To decouple the main application from specific screen implementations, Main depends on the interface ScreenController instead of the concrete class MenuController.
- **InputControlledObject:** InputController depends on the interface InputControlledObject instead of the concrete class UserPlane.

5. **Add interfaces and abstract classes to contain shared / loosely related logic**
- **LevelView:** Make LevelView an abstract class and extract implementation details into the concrete subclass LevelOneView.
- **Display:** Add Display interface to contain common display-related behavior (e.g. show(), playSound()) for GameOverDisplay and WinDisplay.
- **InputControlledObject:** Add InputControlledObject interface to contain all keyboard input-related methods (e.g. moveUp(), stop()) as these are not directly related to the UserPlane object. This interface is implemented by UserPlane.

6. **Extract logic into a standalone class to maintain Single-Responsibility principle**
- **CollisionHandler:** Extract collision handling logic from LevelParent into CollisionHandler. This simplified the extension of collision between bomb and BossPlane for Level 3 and 4.
- **InputController:** Extract keyboard input logic from LevelParent into InputController. This  allowed InputController to remain unchanged as its key-action mappings can be extended in the level classes themselves *(see: Gameplay Enhancement)*.
- **FireAction:** Extract firing logic into FireAction so a single FireAction instance can contain the sound effect, cooldown time and firing logic *(see: Gameplay Enhancement)*. This reduces repetition for fireBullet (using SPACE) and fireBomb (using TAB in Level 3 and 4).
- **ScreenFactory and ScreenLoader:** Extract screen setup logic into ScreenFactory which contains the FXML file paths and uses ScreenLoader which encapsulates the logic of loading FXML and configuring a stage.
- **AlertException:** Extract exception handling logic by displaying an alert window with exception details and gracefully terminating the application into AlertException.
- **SoundLoader and MusicLoader:** Extract sound effect and background music loading into SoundLoader and MusicLoader *(see: Gameplay Enhancement)*.

7. **Move logic from one class to another**
- **BossPlane:** Move toggleShieldVisibility logic from LevelTwo and LevelThree into BossPlane to reduce repetition and improve encapsulation.
- **LevelParent:** Move initializeUnits() logic so that common behavior amongst levels of adding UserPlane to root is done in LevelParent to reduce repetition.

8. **Break long methods into multiple helper methods**
- LevelOne's spawnEnemyUnits() broken down into shouldSpawnEnemy() and createEnemy() for better readability.

9. **Add resetIsDestroyed() to Destructible interface and DestructibleSprite abstract class to maintain Open-closed principle**
- Boolean isDestroyed remains private in DestructibleSprite.
- EnemyBullet and BossBullet call resetIsDestroyed() to reset the state of reused projectiles from the object pool instead of directly accessing the isDestroyed field to maintain Open-closed principle.

10. **Remove redundant method in ActiveActorDestructible (now DestructibleSprite)**
- Remove redundant setDestroyed() method because the boolean parameter is always set to true and hence can be included in the destroy() method.

#### 3) Gameplay Enhancement

1. **Create Level 3 with a new Bomb-Shield action against two BossPlanes**
- **Gameplay:** Player can fire bomb using TAB key to deactivate shields. A sound effect is played if a shield is successfully deactivated upon collision with bomb. The bomb does not affect the health of BossPlanes (so player still needs to use the SPACE key to fire bullets). Player needs to wait a longer period of time between bomb shots (compared to bullet shots). The mechanism of shield visibility based on probability remains unchanged. There are two BossPlanes. The health for UserPlane is doubled to 10.
- **Addition to keyboard-input actions:** The refactored InputController can remain unchanged since a new key-action mapping can be added by overriding instantiateKeyActions() in LevelThree. A FireAction instance which contains the sound effect, cooldown time, and firing logic is used.

2. **Create Level 4 with a new BabyPlane to protect**
- **Gameplay:** This adds a BabyPlane on top of Level 3's gameplay. The BabyPlane is not controlled by the player. The BabyPlane moves randomly and rapidly up and down while aggressively shooting bullets to help UserPlane. However, being a baby, it only has 5 lives (vs 10 for UserPlane) and does not know how to dodge BossBullets. If either BabyPlane or UserPlane dies, the game is over. The key is for UserPlane to strategically be hit by BossBullets (and so protect BabyPlane) while bombing shields and shooting bullets, on top of BabyPlane's bullet-shooting.

3. **Add sound effects and background music**
- **SoundLoader:** Add sound effects using *javafx.scene.media.AudioClip* in SoundLoader. Distinct sounds for firing UserBullet, firing UserBomb, UserPlane taking damage, BabyPlane taking damage, EnemyPlane/BossPlane taking damage, Shield deactivation from bomb collision, GameOver, and Win.
- **MusicLoader:** Add background music using *javafx.scene.media.Media* (more memory-efficient for long-playing sounds) in MusicLoader. Distinct music for MenuScreen, Existing Levels (Level 1 and 2), New Levels (Level 3 and 4).

4. **Add cooldown mechanism in projectile firing by UserPlane**
- A new shot is only allowed after a certain amount of time, set by a constant.
- **Benefit:** Player cannot shoot a consecutive stream of projectiles by holding down the key which looks buggy.
- Bomb (which deactivates shield) has a longer cooldown time than bullets (which decrements health) to make gameplay more dynamic.

5. **Add health progress bar for BossPlanes, kill counter for EnemyPlanes, mini heart container for BabyPlane**
- Add one bar for Level 2 and two bars for each BossPlane for Level 3 and 4. This health progress is a factor of maxHealth.
- Extracted into the utility class ProgressBarWithLabel to encapsulate all related initialization and update.
- Add kill count label for Level 1 showing the number of EnemyPlanes destroyed.
- Add mini heart container for Level 4 to track BabyPlane's health.
- **Benefit:** Provide clear visual of the player's progress in the level.

6. **Add menu screen, how-to-play screen, return buttons**
- Add CSS for styling of all screens, buttons and labels.
- **MenuScreen:** Menu screen provides options to play game, switch to HelpScreen, or exit the application.
- **HelpScreen:** How-to-play screen shows the keyboard actions and descriptions of each level in the game. A button to return to MenuScreen is provided.
- **Return Buttons:** Return buttons on the HelpScreen, GameOverDisplay and WinDisplay allow player to navigate back to the MenuScreen.

7. **Fix hitbox by replacing images with large transparent borders**
- **Problem:** Collision detected despite visually no collision seen.  This is due to old images having large transparent border space. Hence, large hitbox (invisible area surrounding graphic): projectile hits enemy but visually there is still a large space between the graphics.
- **Fix:** Replace image assets for all projectiles and sprites.

8. **Add left and right movement for UserPlane for better dodging**
- Add LEFT and RIGHT key actions for UserPlane to move horizontally to the left and right within allocated screen bounds for better dodging of projectiles and EnemyPlanes.

9. **Other adjustments for more dynamic gameplay**
- Adjust upper and lower bounds for all sprite movements given new sprite sizes (fitted to new image assets).
- Increase velocity of all sprite movements.
- Increase probability of firing bullets for EnemyPlanes.
- Increase probability and maximum number of frames for shield activation.
- BossPlanes can fire BossBullets even when shielded.

### Implemented but Not Working Properly

1. **Projectile image sometimes shown mid-screen instead of from sprite**
- This cannot be reproduced and the reason cannot be traced. It has only happened a few times throughout the many sessions of the game played.
- For example, when a projectile from the Boss is supposed to be displayed starting from the BossPlane sprite, it instead starts appearing in the middle of the screen. The movement and mechanics of the projectile remain normal.

2. **Health Progress Bar for BossPlanes have extra animation**
- This cannot be reproduced and the reason cannot be traced. It has only happened a few times throughout the many sessions of the game played.
- In Level 3 and 4, there are two progress bars. When one bar is completely depleted and the other is not, sometimes the depleted bar will show an animation inside the bar bouncing from left to right. This only happens sometimes. Not sure if it is a built-in animation.

3. **Object Pooling benefit for EnemyBullet is only partial**
> *"Not Working Properly"* is in the sense that the original goal of *full* object pooling for EnemyBullet was not achieved. The object pooling mechanism for EnemyBullet still works without bugs despite partial benefit - see memory benefit analysis under Refactoring.
- **Problem after initial implementation of object pooling:** In a single application launch, when a 2nd game session is started (after win/lose 1st game session), the rendering of EnemyBullets in Level 1 lags.
Interestingly, the rendering of BossBullets (using same object pooling mechanism) in Level 2, 3 and 4 is not affected in all subsequent game sessions in a single application launch.
- **Steps taken:** Logging to console to track every released/reused instance. Resetting the state of reused instances. Add a resetPool method to ObjectPool to clear the stack. 
- **Resolution:** Call a static resetPool method only for EnemyBullets from LevelView. At the end of a game session, clear the stack storing created instances of EnemyBullets. Therefore, Level 1 for a new game session starts with an empty pool. 
- **Conclusion:** This means that although BossBullets can be reused across game levels (Level 2, 3, 4) across game sessions (win/lose -> play again) in a single application launch, EnemyBullets can only be reused throughout the game Level 1 in an individual game session.



### Features Not Implemented

1. **Pause game**
- **Reason:** Time constraint.

2. **Background music volume adjustment**
- **Reason:** Time constraint.

3. **High score and leaderboard**
- **Reason:** Time constraint given anticipated scope since high score will be computed based on time taken to progress to next level (not kill count). Therefore, a timer will need to be implemented for each game level. For the leaderboard, previous high scores need to be stored.

### New Java Classes

**New classes organized by package (src.main.java.com.example.demo):**

1. **.controller (5 new classes):**
- ScreenFactory: to create screens
- ScreenController: interface for screen controllers
- MenuController: controller for menu screen
- HelpController: controller for help screen
- InputController: event handler for keyboard input

2. **.levels (2 new classes):**
- LevelThree: a new playable Level Three for the game
- LevelFour: a new playable Level Four for the game

3. **.models (4 new classes):**
- InputControlledObject: interface for objects which can be controlled by user input
- Poolable: interface for objects which use object pooling
- UserBomb: bomb fired by UserPlane in Level Three
- BabyPlane: baby plane in Level Four

4. **.views (3 new classes):**
- LevelOneView: view for Level One, extracted from old LevelView
- LevelThreeView: view for Level Three, a new level
- LevelFourView: view for Level Four, a new level

5. **.views.components (2 new classes):**
- Display: interface for GameOverDisplay and WinDisplay
- ProgressBarWithLabel: generic class for a progress bar with associated label

6. **.utils (7 new classes):**
- AlertException: handle exceptions
- CollisionHandler: handle collisions
- FireAction: runnable for projectile firing
- ObjectPool: object pool to reuse objects
- MusicLoader: load music
- SoundLoader: load sound effects
- ScreenLoader: load screen, used by ScreenFactory

**Test classes organized by package (src.test.java.com.example.demo):**

1. **.levels (4 new classes):**
- LevelOneTest: test class for Level One
- LevelTwoTest: test class for Level Two
- LevelThreeTest: test class for Level Three
- LevelFourTest: test class for Level Four

2. **.models (4 new classes):**
- BossPlaneTest: test class for behavior of BossPlane
- EnemyPlaneTest: test class for behavior of EnemyPlane
- UserPlaneTest: test class for behavior of UserPlane
- BabyPlaneTest: test class for behavior of BabyPlane

### Unmodified Java Classes
1. Projectile
2. FighterPlane *(only modification is renaming to Plane)*

### Modified Java Classes
Organized by original source code structure:
> The reason behind the modifications listed may overlap with the details in "Implemented and Working Properly" - Refactoring section.

1. **Main**
- Depend on interface ScreenController instead of concrete class to maintain Dependency Inversion principle.
- Use ScreenFactory to create MenuScreen to provide a landing page when the application starts.

2. **Controller** (new: GameController)
- Replace deprecated Observer with InvalidationListener which offer a more updated support of the original Observer design pattern implementation.
- Correctly removing observable or listener when transitioning to the next level to fix the original bug of slow loading of subsequent level.
- Add CSS stylesheet to provide visual enhancement for the components such as buttons in the game.

3. **ActiveActor** (new: Sprite)
- Require non-null file path when setting the image for the ImageView as an additional check.

4. **ActiveActorDestructible** (new: DestructibleSprite)
- Add resetIsDestroyed() which is necessary since ActiveActorDestructible implements Destructible.

5. **Boss** (new: BossPlane)
- Completed the shield functionality which was incomplete in the original source code.
- Add sound effect when plane takes damage for a better user experience.
- Use create() for BossBullet instead of constructor (which is set to private) which implements object pooling to optimize memory.

6. **BossProjectile** (new: BossBullet)
- Add object pooling mechanism to reuse instances to optimize memory.
- Implement Poolable interface since behavior associated with the object pooling mechanism is not directly related to the nature of a projectile.

7. **Destructible**
- Add resetIsDestroyed() which resets the state of the projectile's isDestroyed boolean to false. This is used to implement the object pooling mechanism to reuse instances of BossBullet and EnemyBullet.

8. **EnemyPlane**
- Add sound effect when plane takes damage for a better user experience.
- Use create() for EnemyBullet instead of constructor (which is set to private) which implements object pooling to optimize memory.

9. **EnemyProjectile** (new: EnemyBullet)
- Add object pooling mechanism to reuse instances to optimize memory.
- Implement Poolable interface since behavior associated with the object pooling mechanism is not directly related to the nature of a projectile.

10. **GameOverImage** (new: GameOverDisplay)
- Add sound effect when shown for a better user experience.
- Implement Display interface which organizes the common display-related behavior between GameOverImage and WinImage.

11. **HeartDisplay** (new: HeartContainer)
- Add sound effect when heart is removed for a better user experience.

12. **LevelOne**
- Add background music for a better user experience.
- Fix the bug of shield visibility being affected by not instantiating the correct level view when the level is called via reflection in the controller.
- Add function to update the kill count label with the kill count of EnemyPlanes which helps the player track their progress in the game.

13. **LevelParent**
- Replace deprecated Observable with StringProperty which offer a more updated support of the original Observer design pattern implementation.
- Use MusicLoader to add background music for a better user experience.
- Use CollisionHandler in collision handling to maintain single-responsibility principle.
- Use InputController and FireAction in instantiating key actions to maintain single-responsibility principle.
- Require non-null file path when setting the image for the ImageView as an additional check.
- Correctly stop the timeline during level transition to fix the bug of previous level's functions being called in the middle of the gameplay for a current level.
- Add handling of sprites moving offscreen so they are destroyed (and released via the object pooling mechanism) to optimize memory.
- UpdateActors() refers to abstract class DestructibleSprite instead of its subclasses to maintain Dependency Inversion principle.

14. **LevelTwo**
- Add background music for a better user experience.
- Fix the bug of shield visibility being affected by not instantiating the correct level view when the level is called via reflection in the controller.
- Add the functions related to shield visibility which was an incomplete feature in the original source code.
- Add function to update the boss health progress bar with the health of BossPlane which helps the player track their progress in the game.

15. **LevelView**
- Add return button to return to menu screen for navigation after a game session is completed.
- Clear pool for EnemyBullet to fix the bug of EnemyBullets not rendering in subsequent game sessions in a single application launch after the initial implementation of object pooling.

16. **LevelViewLevelTwo** (new: LevelTwoView)
- Add health progress bar for BossPlane to help player track their progress in the game.

17. **ShieldImage** (new: Shield)
- Fix typo in image file path from the original source code which gave a null pointer exception.
- Require non-null file path when setting the image for the ImageView as an additional check.

18. **UserPlane**
- Add horizontal movement (left and right) for more dynamic gameplay.
- Add ability to fire bomb to break shield of BossPlane for more dynamic gameplay.
- Implement InputControlledObject interface since behavior associated with the keyboard actions is not directly related to the nature of a plane.

19. **UserProjectile** (new: UserBullet)
- Adjust constants which determine movement for more dynamic gameplay.

20. **WinImage** (new: WinDisplay)
- Add sound effect when shown for a better user experience.
- Implement Display interface which organizes the common display-related behavior between GameOverImage and WinImage.

### Unexpected Problems

1. **Initial extraction of file path constants into a global class**
- An early refactoring attempt was to extract all file path constants (image and audio file paths) into a global class of symbolic constants which would be called by all the classes to set their image or audio. The initial reasoning was for a single place to encapsulate the file paths of game resources for more convenient control and access.
- This was later reverted after I learnt that having the relevant symbolic constant in the class itself where it is called helps reduce rebuild time.

2. **Initial extraction of Background and Heart into standalone classes**
- An early refactoring attempt was to extract Background (representing the background image in a game level) from LevelParent and Heart from HeartContainer into standalone classes. The initial reasoning was for single-responsibility and potential future reusability.
- This was later reverted after I learnt that these two classes are data classes without any behaviour (and hence, a code smell) from the lectures and the website refactoring.guru recommended in the lectures.
