package com.example.demo.controller;

import com.example.demo.UserPlane;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.util.Map;

/**
 * Class implementing {@code EventHandler<KeyEvent>} to handle all keyboard input and support multiple actions using key-action mappings.
 * <p>
 * Direct access to {@link UserPlane} since the plane is the only user-controlled object in this single-player game.
 */
public class InputController implements EventHandler<KeyEvent> {
    private final UserPlane user;
    private final Map<KeyCode, Runnable> keyActions;

    /**
     * Constructor to create an instance of InputController.
     *
     * @param user - the UserPlane representing the player
     * @param keyActions - a map of key codes to actions (Runnable) to be executed when the key is pressed
     */
    public InputController(UserPlane user, Map<KeyCode, Runnable> keyActions) {
        this.user = user;
        this.keyActions = keyActions;
    }

    /**
     * Handle both key pressed and key released events.
     * 
     * @param event - the event representing a key pressed or key released
     */
    @Override
    public void handle(KeyEvent event) {
        KeyCode keyCode = event.getCode();
        if (event.getEventType() == KeyEvent.KEY_PRESSED) {
            handleKeyPressed(keyCode);
        } else if (event.getEventType() == KeyEvent.KEY_RELEASED) {
            handleKeyReleased(keyCode);
        }
    }

    /**
     * Handle key pressed events.
     *
     * @param keyCode - the keyboard key pressed
     */
    private void handleKeyPressed(KeyCode keyCode) {
        switch (keyCode) {
            case UP:
                user.moveUp();
                break;
            case DOWN:
                user.moveDown();
                break;
            case LEFT:
                user.moveLeft();
                break;
            case RIGHT:
                user.moveRight();
                break;
            default:
                Runnable action = keyActions.get(keyCode);
                if (action != null) {
                    action.run();
                }
                break;
        }
    }

    /**
     * Handle key released events.
     *
     * @param keyCode - the keyboard key released
     */
    private void handleKeyReleased(KeyCode keyCode) {
        if (keyCode == KeyCode.UP || keyCode == KeyCode.DOWN || keyCode == KeyCode.LEFT || keyCode == KeyCode.RIGHT) {
            user.stop();
        }
    }
}
