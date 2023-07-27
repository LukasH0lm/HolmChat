package com.lukash0lm.holmchat.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> get(long id);

    List<T> getAll();

    void save(T t) throws SQLException;

    void update(T t, String[] params);

    void delete(T t);
}
