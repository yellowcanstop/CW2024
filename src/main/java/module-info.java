module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo.controller;
    exports com.example.demo.utils;
    opens com.example.demo.views to javafx.fxml;
    opens com.example.demo.levels to javafx.fxml;
    opens com.example.demo.views.components to javafx.fxml;
    exports com.example.demo;
    exports com.example.demo.models;
    opens com.example.demo.models to javafx.fxml;
}