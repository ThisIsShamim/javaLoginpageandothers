package com.example.loginpage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;


public class MainFrameController {
    @FXML
    private AnchorPane ancorepane,ancorepane1,ancorepane2;

    @FXML
    private AnchorPane movableAnchorPane;

    private double offsetX;
    private double offsetY;

    @FXML
    public void handleMousePressed(javafx.scene.input.MouseEvent event) {
        // Capture the current mouse position relative to the AnchorPane
        offsetX = event.getSceneX() - movableAnchorPane.getLayoutX();
        offsetY = event.getSceneY() - movableAnchorPane.getLayoutY();
    }

    @FXML
    public void handleMouseDragged(javafx.scene.input.MouseEvent event) {
        // Update the AnchorPane's position based on the mouse movement
        movableAnchorPane.setLayoutX(event.getSceneX() - offsetX);
        movableAnchorPane.setLayoutY(event.getSceneY() - offsetY);
    }
}
