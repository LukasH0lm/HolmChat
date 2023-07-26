package com.lukash0lm.holmchat.ControlObjects;

import java.sql.Timestamp;

public class User {

    int id;
    String name;
    String Encryptedpassword;

    String salt;
    Timestamp creationDate;


    public User(int id, String name, String password, String salt, Timestamp creationDate) {
        this.id = id;
        this.name = name;

        this.Encryptedpassword = password;

        this.salt = salt;

        this.creationDate = creationDate;
    }




}
