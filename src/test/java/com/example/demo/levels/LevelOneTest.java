package com.example.demo.levels;

import static org.mockito.Mockito.*;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.mockito.*;

/**
 * Test class for game behaviour in Level One.
 */
public class LevelOneTest extends ApplicationTest {
    public static final int SCREEN_WIDTH = 1300;
    public static final int SCREEN_HEIGHT = 750;

    /**
     * Mock object of level one using Mockito.
     */
    @Mock
    private LevelOne levelOne;

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
        levelOne = spy(new LevelOne(SCREEN_HEIGHT, SCREEN_WIDTH));
        stage.setScene(new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT));
        stage.show();
    }

    /**
     * Test if the game is over and does not proceed to next level when user plane is destroyed.
     */
    @Test
    public void testGameOver() {
        doReturn(true).when(levelOne).userIsDestroyed();
        doNothing().when(levelOne).loseGame();
        levelOne.checkIfGameOver();
        verify(levelOne, times(1)).loseGame();
        verify(levelOne, never()).goToNextLevel(anyString());
    }

    /**
     * Test if the game proceeds to next level when user plane has reached kill target.
     */
    @Test
    public void testGameWon() {
        doReturn(false).when(levelOne).userIsDestroyed();
        doReturn(true).when(levelOne).userHasReachedKillTarget();
        doNothing().when(levelOne).goToNextLevel(anyString());
        levelOne.checkIfGameOver();
        verify(levelOne, times(1)).goToNextLevel(LevelOne.NEXT_LEVEL);
        verify(levelOne, never()).loseGame();
    }

    /**
     * Test if the game is not over when no conditions are met.
     */
    @Test
    public void testGameContinues() {
        doReturn(false).when(levelOne).userIsDestroyed();
        doReturn(false).when(levelOne).userHasReachedKillTarget();
        levelOne.checkIfGameOver();
        verify(levelOne, never()).loseGame();
        verify(levelOne, never()).goToNextLevel(anyString());
    }
}