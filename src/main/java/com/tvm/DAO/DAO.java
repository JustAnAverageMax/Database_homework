package com.tvm.DAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    List<Integer> getAllIds() throws SQLException;
    List<T> getAll() throws SQLException;
    void save(T entity) throws SQLException;
    Optional<T> getById(int id) throws SQLException;
    void update(int id, T entity) throws SQLException;
    void deleteById(int id) throws SQLException;
}
