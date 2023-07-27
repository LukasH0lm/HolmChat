package com.lukash0lm.holmchat.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MessageDao implements Dao {


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

    }

    @Override
    public void update(Object o, String[] params) {

    }

    @Override
    public void delete(Object o) {

    }
}
