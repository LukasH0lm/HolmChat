package com.lukash0lm.holmchat.ControlObjects;

import java.sql.Timestamp;

public class User {

    private int id;
    private String name;
    private String EncryptedPassword;
    private String salt;
    private Timestamp creationTimestamp;


    public User(int id, String name, String password, String salt, Timestamp creationTimestamp) {
        this.id = id;
        this.name = name;

        this.EncryptedPassword = password;

        this.salt = salt;

        this.creationTimestamp = creationTimestamp;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEncryptedpassword() {
        return EncryptedPassword;
    }

    public void setEncryptedpassword(String encryptedpassword) {
        EncryptedPassword = encryptedpassword;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Timestamp getcreationTimestamp() {
        return creationTimestamp;
    }

    public void setcreationTimestamp(Timestamp creationDate) {
        this.creationTimestamp = creationDate;
    }
}
