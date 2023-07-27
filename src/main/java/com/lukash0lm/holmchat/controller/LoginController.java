package com.lukash0lm.holmchat.controller;

import com.lukash0lm.holmchat.ControlObjects.User;
import com.lukash0lm.holmchat.Dao.UserDao;
import com.lukash0lm.holmchat.HelloApplication;
import com.lukash0lm.holmchat.utility.PasswordEncryptor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;


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
    void onLoginButtonClicked(ActionEvent event) throws Exception {

        System.out.println("Login button clicked");

        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            Alert.AlertType type = Alert.AlertType.ERROR;
            Alert alert = new Alert(type, "Please enter a username and password");
            alert.showAndWait();
            return;
        }

        UserDao userDao = new UserDao();

        User user = userDao.getUser(username);

        if (user == null) {
            Alert.AlertType type = Alert.AlertType.ERROR;
            Alert alert = new Alert(type, "Username does not exist");
            alert.showAndWait();
            return;
        }

        PasswordEncryptor encryptor = new PasswordEncryptor();

        String salt = user.getSalt();


        String encryptedPassword = encryptor.getEncryptedPassword(password, salt);

        //opens chat window
        if (Objects.equals(encryptedPassword, user.getEncryptedpassword())) {
            System.out.println("Login successful");

            Stage stage = (Stage) LoginButton.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/chat.fxml"));

            Parent root = fxmlLoader.load();
            ChatController controller = fxmlLoader.<ChatController>getController();
            controller.setUser(user);
            Scene scene = new Scene(root);

            stage.setScene(scene);

            stage.show();



        } else {

            Alert.AlertType type = Alert.AlertType.ERROR;
            Alert alert = new Alert(type, "Incorrect password"); //TODO: make this more secure, only for testing
            alert.showAndWait();

        }

    }


    @FXML
    private void SignUpButtonClicked(ActionEvent event) throws Exception {


        if (usernameTextField.getText().isEmpty() ||
                passwordTextField.getText().isEmpty()) {
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



}
