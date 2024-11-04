package com.example.demo.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Utility class for handling exceptions by displaying an alert dialog.
 */
public class AlertUtils {
	/**
	 * Displays an alert dialog with the exception message.
	 * @param e - the exception to display
	 */
    public static void alertException(Exception e) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setContentText(e.getClass() + ":\n" + e.getMessage());
		alert.showAndWait();
		System.exit(1);
	}
}
