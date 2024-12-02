package com.example.loginpage;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class loginController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private Label loginmassegeLabel;
    @FXML
    private ImageView fileImageView;
    @FXML
    private ImageView lockImageView;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Button registerButton;
    @FXML
    Alert alert;
 /*   @FXML
    private Rectangle background;
    private int currentImageIndex = 0;

    // Array of image URLs
    private final String[] images = {
            getClass().getResource("/photo2.jpg").toExternalForm(),
            getClass().getResource("/photo_2024-10-27_18-36-19.jpg").toExternalForm(),
            getClass().getResource("/shoreham-and-tides-chicago-il-primary-photo.jpg").toExternalForm()
    };
*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

/*
        // Initial background image
        //ImagePattern imagePattern = new ImagePattern(new Image(images[currentImageIndex]));
        ImageView imageView = new ImageView(new Image(images[currentImageIndex]));
        imageView.setOpacity(0.0);
        ImagePattern imagePattern = new ImagePattern(imageView.getImage());
        background.setFill(imagePattern);

        // Timeline to change the background image every 3 seconds
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), e -> {
            currentImageIndex = (currentImageIndex + 1) % images.length;
            background.setFill(new ImagePattern(new Image(images[currentImageIndex])));
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();*/


        // Add a listener to monitor changes in the text property of the TextField which is related to css file
            usernameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                // Check if the TextField contains text
                if (newValue.isEmpty()) {
                    // Remove the CSS class when the text field is empty
                    usernameTextField.getStyleClass().remove("has-text");
                } else {
                    // Add the CSS class when there is text in the text field
                    if (!usernameTextField.getStyleClass().contains("has-text")) {
                        usernameTextField.getStyleClass().add("has-text");
                    }
                }
            });

        // Add a listener to monitor changes in the text property of the passwordfield which is related to css file
        passwordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Check if the TextField contains text
            if (newValue.isEmpty()) {
                // Remove the CSS class when the text field is empty
                passwordTextField.getStyleClass().remove("has-text");
            } else {
                // Add the CSS class when there is text in the text field
                if (!passwordTextField.getStyleClass().contains("has-text")) {
                    passwordTextField.getStyleClass().add("has-text");
                }
            }
        });
       /* File file = new File("src/main/resources/pic.jpg");
        Image image = new Image(file.toURI().toString());
        fileImageView.setImage(image);

        File lockImage = new File("src/main/resources/icons8-lock-94.png");
        Image image1 = new Image(file.toURI().toString());
        lockImageView.setImage(image1);*/

    }

    public void loginButtonOnAction(ActionEvent event){
        if(usernameTextField.getText().isBlank()==false && passwordTextField.getText().isBlank()==false){
            validateLogin();
        }else{

            if(usernameTextField.getText().isBlank()==true){
                loginmassegeLabel.setTextFill(Color.RED);
                loginmassegeLabel.setText("");


                // Create and show an alert pop-up
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Input Error");
                alert.setHeaderText("Missing Information");
                alert.setContentText("Phone number cannot be blank!!");

                alertEffect();

            }

            else if(passwordTextField.getText().isBlank()==true){
                loginmassegeLabel.setTextFill(Color.RED);
                loginmassegeLabel.setText("");


                // Create and show an alert pop-up
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Input Error");
                alert.setHeaderText("Missing Information");
                alert.setContentText("Please enter your password!!");

                alertEffect();

            }
        }
    }

    //cancel button on action
    public void cancelButtonOnAction(ActionEvent event){

        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();

    }

    public void validateLogin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM new_table WHERE phonenumber = ? AND password = ?";
        try{
            PreparedStatement preparedStatement = connectDB.prepareStatement(verifyLogin);
            preparedStatement.setString(1, usernameTextField.getText().trim());
            preparedStatement.setString(2, passwordTextField.getText().trim());
            ResultSet queryResult = preparedStatement.executeQuery();

            while (queryResult.next()) {
                loginmassegeLabel.setTextFill(Color.RED);
                if ((queryResult.getInt(1)==1)) {

                    loginmassegeLabel.setTextFill(Color.GREEN);
                    loginmassegeLabel.setText("Congratulations! Login Successful.");

                    //closeLoginFrame();
                    //createAccountForm();

                } else {
                    loginmassegeLabel.setText("Invalid Login. Please try again!");
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public  void clickRegisterButton(ActionEvent event){
        try{
            Stage registerStage = new Stage();

            FXMLLoader loader = new FXMLLoader(registerApplication.class.getResource("register.fxml"));
            Scene scene = new Scene(loader.load());
            //adding image icon
            Image realstate = new Image(new FileInputStream("C:\\Users\\USER\\OneDrive\\Desktop\\3RD SEMISTER\\JAVA\\LoginPage\\src\\main\\resources\\realstateicon.png"));
            registerStage.getIcons().add(realstate);
            registerStage.setScene(scene);
            registerStage.show();
            Stage stage = (Stage) registerButton.getScene().getWindow();

            stage.close();


        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void alertEffect(){
        alert.getDialogPane().getStylesheets().add(getClass().getResource("Alert.css").toExternalForm());
        alert.getDialogPane().getStyleClass().add("alert");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

        // Add a fade-in transition
        FadeTransition fadeIn = new FadeTransition(Duration.millis(1000), stage.getScene().getRoot());
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.setCycleCount(1);
        fadeIn.play();

        // Show the Alert
        alert.showAndWait();


    }



}
