package com.lukash0lm.holmchat.Dao;

import com.lukash0lm.holmchat.ConnectionSingleton;
import com.lukash0lm.holmchat.ControlObjects.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao{


    Connection con = ConnectionSingleton.getInstance().getConnection();

    public UserDao() throws SQLException, IOException {
    }

    @Override
    public Optional get(long id) {
        return Optional.empty();
    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public void save(Object o) {

    }

    @Override
    public void update(Object o, String[] params) {

    }

    @Override
    public void delete(Object o) {

    }

    public User getUser(String username) throws SQLException {

        PreparedStatement ps = con.prepareStatement("SELECT * FROM Users WHERE username = ?");

        ps.setString(1, username);

        return new User(ps.executeQuery().getInt("id"), ps.executeQuery().getString("username"), ps.executeQuery().getString("password"), ps.executeQuery().getString("salt"), ps.executeQuery().getTimestamp("creationDate"));

    }

    public boolean isUsernameTaken(String username) throws SQLException {

        PreparedStatement ps = con.prepareStatement("SELECT * FROM Users WHERE username = ?");

        ps.setString(1, username);

        return ps.executeQuery().next();


    }

}
