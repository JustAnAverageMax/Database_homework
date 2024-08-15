package com.tvm.DAO;

import java.sql.SQLException;
import java.util.Optional;

public interface DAO<T> {
    void save(T entity) throws SQLException;
    Optional<T> getById(int id) throws SQLException;
    void update(int id, T entity) throws SQLException;
    void deleteById(int id) throws SQLException;
}
