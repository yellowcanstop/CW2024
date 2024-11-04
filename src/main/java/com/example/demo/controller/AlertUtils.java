package com.example.demo.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertUtils {
    public static void alertException(Exception e) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setContentText(e.getClass() + ":\n" + e.getMessage());
		alert.show();
		System.exit(1);
	}
}
