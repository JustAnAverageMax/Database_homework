package com.tvm.DAO;

import com.tvm.Model.Ticket;
import com.tvm.Model.User;
import com.tvm.constants.Queries;
import com.tvm.constants.TicketType;
import com.tvm.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO implements DAO<User> {

    public List<Integer> getAllIds() throws SQLException {
        String query = Queries.GET_ALL_USER_IDS;
        List<Integer> ids = new ArrayList<>();
        try (Connection conn = DBUtil.connect(); Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                ids.add(resultSet.getInt("id"));
            }
        }

        return ids;
    }


    @Override
    public List<User> getAll() throws SQLException {
        String query = Queries.GET_ALL_USERS;
        List<User> users = new ArrayList<>();

        try (Connection conn = DBUtil.connect(); Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getTimestamp("creation_date").toLocalDateTime(),
                        resultSet.getString("name")
                );
                users.add(user);
            }
        }
        return users;
    }

    @Override
    public void save(User entity) throws SQLException {
        String query = Queries.SAVE_USER;

        try (Connection conn = DBUtil.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, entity.getName());
            pstmt.executeUpdate();
        }
    }

    @Override
    public Optional<User> getById(int id) throws SQLException {
        String query = Queries.GET_USER_BY_ID;
        Optional<User> user = Optional.empty();

        try (Connection conn = DBUtil.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                user = Optional.of(new User(
                        resultSet.getInt("id"),
                        resultSet.getTimestamp("creation_date").toLocalDateTime(),
                        resultSet.getString("name")
                ));
            }
        }

        return user;
    }

    @Override
    public void update(int id, User entity) throws SQLException {
        String query = Queries.UPDATE_USER;

        try (Connection conn = DBUtil.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, entity.getName());
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deleteById(int id) throws SQLException {
        String query = Queries.DELETE_USER_BY_ID;

        try (Connection conn = DBUtil.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
