module com.lukash0lm.holmchat {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.lukash0lm.holmchat to javafx.fxml;
    exports com.lukash0lm.holmchat;
}