package com.lukash0lm.holmchat.controller;

import com.lukash0lm.holmchat.ControlObjects.User;
import com.lukash0lm.holmchat.Dao.UserDao;
import com.lukash0lm.holmchat.utility.PasswordEncryptor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Timestamp;
import java.time.LocalDateTime;


public class LoginController {


    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button LoginButton;

    @FXML
    private Button SignUpButton;

    @FXML
    void SignUpButtonClicked(ActionEvent event) throws Exception {


        if (usernameTextField.getText().equals("") || passwordTextField.getText().equals("")) {
            Alert.AlertType type = Alert.AlertType.ERROR;
            Alert alert = new Alert(type, "Please enter a username and password");
            alert.showAndWait();
            return;
        }


        PasswordEncryptor encryptor = new PasswordEncryptor();

        String username = usernameTextField.getText();

        String password = passwordTextField.getText();


        String salt = encryptor.getNewSalt();
        String encryptedPassword = encryptor.getEncryptedPassword(password, salt);

        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);


        User user = new User(-1, username, encryptedPassword, salt, timestamp);

        UserDao userDao = new UserDao();

        //check if username already exists
        //if it does, display error message

        if (userDao.isUsernameTaken(username)) {

            Alert.AlertType type = Alert.AlertType.ERROR;
            Alert alert = new Alert(type, "Username already taken");
            alert.showAndWait();


        } else {

            userDao.save(user);

            Alert.AlertType type = Alert.AlertType.INFORMATION;
            Alert alert = new Alert(type, "Account created");
            alert.showAndWait();

        }



    }

    @FXML
    void onLoginButtonClicked(ActionEvent event) {

        System.out.println("Login button clicked");





    }

}
