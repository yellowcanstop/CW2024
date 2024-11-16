package com.example.demo.controller;

import com.example.demo.models.InputControlledObject;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.util.Map;

/**
 * Class implementing {@code EventHandler<KeyEvent>} to handle all keyboard input.
 * <p>
 * For extensibility, key-action mappings are used to support multiple actions (e.g. TAB to fireBomb).
 */
public class InputController implements EventHandler<KeyEvent> {
    private final InputControlledObject inputControlledObject;
    private final Map<KeyCode, Runnable> keyActions;

    /**
     * Constructor to create an instance of InputController.
     *
     * @param inputControlledObject - the object controlled by player's input
     * @param keyActions - a map of key codes to actions (Runnable) to be executed when the key is pressed
     */
    public InputController(InputControlledObject inputControlledObject, Map<KeyCode, Runnable> keyActions) {
        this.inputControlledObject = inputControlledObject;
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
                inputControlledObject.moveUp();
                break;
            case DOWN:
                inputControlledObject.moveDown();
                break;
            case LEFT:
                inputControlledObject.moveLeft();
                break;
            case RIGHT:
                inputControlledObject.moveRight();
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
            inputControlledObject.stop();
        }
    }
}
