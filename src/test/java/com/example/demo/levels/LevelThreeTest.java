package com.example.demo.levels;

import static org.mockito.Mockito.*;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.mockito.*;

/**
 * Test class for game behaviour in Level Three.
 */
public class LevelThreeTest extends ApplicationTest {
    public static final int SCREEN_WIDTH = 1300;
    public static final int SCREEN_HEIGHT = 750;

    /**
     * Mock object of level three using Mockito.
     */
    @Mock
    private LevelThree levelThree;

    /**
     * Start method for the JavaFX application, required for ApplicationTest.
     * <p>
     * If TestFX is not used, throws RuntimeException: Internal graphics not initialized yet.
     *
     * @param stage - the primary stage for the JavaFX application
     */
    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        MockitoAnnotations.openMocks(this);
        levelThree = spy(new LevelThree(SCREEN_HEIGHT, SCREEN_WIDTH));
        stage.setScene(new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT));
        stage.show();
    }

    /**
     * Test if the game is over and does not proceed to next level when user plane is destroyed.
     */
    @Test
    public void testGameOver() {
        doReturn(true).when(levelThree).userIsDestroyed();
        doNothing().when(levelThree).loseGame();
        levelThree.checkIfGameOver();
        verify(levelThree, times(1)).loseGame();
        verify(levelThree, never()).winGame();
    }

    /**
     * Test if the game is won when the boss planes are destroyed.
     */
    @Test
    public void testGameWon() {
        doReturn(false).when(levelThree).userIsDestroyed();
        doReturn(true).when(levelThree).bossesAreDestroyed();
        levelThree.checkIfGameOver();
        verify(levelThree, times(1)).winGame();
        verify(levelThree, never()).loseGame();
    }

    /**
     * Test if the game is not over when no conditions are met.
     */
    @Test
    public void testGameContinues() {
        doReturn(false).when(levelThree).userIsDestroyed();
        doReturn(false).when(levelThree).bossesAreDestroyed();
        levelThree.checkIfGameOver();
        verify(levelThree, never()).loseGame();
        verify(levelThree, never()).winGame();
    }
}