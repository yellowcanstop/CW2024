package com.example.demo.levels;

import static org.mockito.Mockito.*;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.mockito.*;

/**
 * Test class for game behaviour in Level Two.
 */
public class LevelTwoTest extends ApplicationTest {
    public static final int SCREEN_WIDTH = 1300;
    public static final int SCREEN_HEIGHT = 750;

    /**
     * Mock object of level two using Mockito.
     */
    @Mock
    private LevelTwo levelTwo;

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
        levelTwo = spy(new LevelTwo(SCREEN_HEIGHT, SCREEN_WIDTH));
        stage.setScene(new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT));
        stage.show();
    }

    /**
     * Test if the game is over and does not proceed to next level when user plane is destroyed.
     */
    @Test
    public void testGameOver() {
        doReturn(true).when(levelTwo).userIsDestroyed();
        doNothing().when(levelTwo).loseGame();
        levelTwo.checkIfGameOver();
        verify(levelTwo, times(1)).loseGame();
        verify(levelTwo, never()).goToNextLevel(anyString());
    }

    /**
     * Test if the game proceeds to next level when boss plane is destroyed.
     */
    @Test
    public void testGameWon() {
        doReturn(false).when(levelTwo).userIsDestroyed();
        doReturn(true).when(levelTwo).bossIsDestroyed();
        doNothing().when(levelTwo).goToNextLevel(anyString());
        levelTwo.checkIfGameOver();
        verify(levelTwo, times(1)).goToNextLevel(LevelTwo.NEXT_LEVEL);
        verify(levelTwo, never()).loseGame();
    }

    /**
     * Test if the game is not over when no conditions are met.
     */
    @Test
    public void testGameContinues() {
        doReturn(false).when(levelTwo).userIsDestroyed();
        doReturn(false).when(levelTwo).bossIsDestroyed();
        levelTwo.checkIfGameOver();
        verify(levelTwo, never()).loseGame();
        verify(levelTwo, never()).goToNextLevel(anyString());
    }
}