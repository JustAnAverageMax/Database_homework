package com.tvm.DAO;

import com.tvm.Model.Ticket;
import com.tvm.Model.User;
import com.tvm.constants.Queries;
import com.tvm.utils.DBUtil;

import java.sql.*;
import java.util.Optional;

public class UserDAO implements DAO<User> {

    public void deleteByTicket(Ticket ticket)throws SQLException{
        String query = Queries.DELETE_USER_BY_ID;

        try(Connection conn = DBUtil.connect(); PreparedStatement pstmp = conn.prepareStatement(query)){
            pstmp.setLong(1, ticket.getUserId());
            pstmp.executeUpdate();
        }
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
