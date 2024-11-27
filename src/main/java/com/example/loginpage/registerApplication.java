package com.example.loginpage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class registerApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(registerApplication.class.getResource("register.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);

        primaryStage.show();

    }
}
