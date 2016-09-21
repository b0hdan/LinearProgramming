package com.dubyniak.bohdan.linearprogramming.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Parent mainRoot;

    @Override
    public void start(Stage primaryStage) throws Exception{
        mainRoot = FXMLLoader.load(getClass().getResource("../fxml/main.fxml"));
        primaryStage.setTitle("Linear programming");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(mainRoot, 500, 498));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
