package com.lukash0lm.holmchat.controller;

import com.lukash0lm.holmchat.ControlObjects.User;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class ChatController {


    User currentUser;

    @FXML private void initialize() {

        Platform.runLater(() -> {

            //do stuff

            System.out.println("ChatController initialized");
            System.out.println("Current user: " + currentUser.getName());

        });

    }



    @FXML
    void sendMessage(MouseEvent event) {



        System.out.println("Send message button clicked");

        String message = messageTextArea.getText();

        if (message.isEmpty()) {
            return;
        }

        chatTextArea.appendText(currentUser.getName() + ": " + message + "\n");
        messageTextArea.clear();

    }

    public void setUser(User user) {

        currentUser = user;

    }


    @FXML
    private TextArea chatTextArea;

    @FXML
    private VBox friendVBox;

    @FXML
    private TextArea messageTextArea;

    @FXML
    private Button sendButton;



}
