module com.lukash0lm.holmchat {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.lukash0lm.holmchat to javafx.fxml;
    exports com.lukash0lm.holmchat;

    opens com.lukash0lm.holmchat.controller to javafx.fxml;
    exports com.lukash0lm.holmchat.controller;

}