package com.example.loginpage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class VideoBackgroundApp extends Application { // Make the class public

    public VideoBackgroundApp() {
        // Explicit no-argument constructor (optional but good practice)
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Path to the video file
        String videoPath = getClass().getResource("/6510697-hd_1920_1080_30fps.mp4").toExternalForm();

        // Create Media and MediaPlayer
        Media media = new Media(videoPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        // Set MediaView
        MediaView mediaView = new MediaView(mediaPlayer);

        // Adjust MediaView to fill the window
        mediaView.setPreserveRatio(false);
        mediaView.fitWidthProperty().bind(primaryStage.widthProperty());
        mediaView.fitHeightProperty().bind(primaryStage.heightProperty());

        // Create a layout and add the MediaView
        StackPane root = new StackPane();
        root.getChildren().add(mediaView);


        // Start the video loop
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Default loop
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(mediaPlayer.getStartTime())); // Manual loop (backup)
        mediaPlayer.play();

        // Create and set up the scene
        Scene scene = new Scene(root,800,800);


        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
