package com.lukash0lm.holmchat.Dao;

import com.lukash0lm.holmchat.ConnectionSingleton;
import com.lukash0lm.holmchat.ControlObjects.User;

import java.io.IOException;
import java.sql.*;
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
    public void save(Object o) throws SQLException {

        User user = (User) o;

        PreparedStatement ps = con.prepareStatement("INSERT INTO users (username, password, salt, creation_datetime) VALUES (?, ?, ?, ?)");

        ps.setString(1, user.getName());
        ps.setString(2, user.getEncryptedpassword());
        ps.setString(3, user.getSalt());
        ps.setTimestamp(4, user.getcreationTimestamp());


        ps.executeUpdate();


    }

    @Override
    public void update(Object o, String[] params) {

    }

    @Override
    public void delete(Object o) {

    }

    public User getUser(String username) throws SQLException {

        PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username = ?");

        ps.setString(1, username);


        ResultSet rs = ps.executeQuery();

        if (!rs.next()) {
            return null;
        }

        int id = rs.getInt("id");
        String name = rs.getString("username");
        String password = rs.getString("password");
        String salt = rs.getString("salt");
        Timestamp creationDate = rs.getTimestamp("creation_datetime");

        return new User(id, name, password, salt, creationDate);


    }

    public boolean isUsernameTaken(String username) throws SQLException {

        PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username = ?");

        ps.setString(1, username);

        return ps.executeQuery().next();


    }

}
