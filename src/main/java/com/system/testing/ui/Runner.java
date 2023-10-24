package com.system.testing.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class Runner extends Application {

    private  Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(new File("sample.fxml").toURL());
        primaryStage.setTitle("Калькулятор");
        primaryStage.setScene(new Scene(root, 525, 500));
        primaryStage.setResizable(false);
        primaryStage.show();
        controller = new Controller();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
