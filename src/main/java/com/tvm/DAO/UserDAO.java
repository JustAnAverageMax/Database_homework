package com.tvm.DAO;

import com.tvm.Model.Ticket;
import com.tvm.Model.User;
import com.tvm.utils.DBUtil;

import java.sql.*;

public class UserDAO implements DAO<User> {

    public void deleteByTicket(Ticket ticket)throws SQLException{
        String query = "DELETE FROM \"user\" WHERE id = ?";

        try(Connection conn = DBUtil.connect(); PreparedStatement pstmp = conn.prepareStatement(query)){
            pstmp.setLong(1, ticket.getUserId());
            pstmp.executeUpdate();
        }
    }

    @Override
    public void save(User entity) throws SQLException {
        String query = "INSERT INTO \"user\" (name) VALUES (?)";

        try (Connection conn = DBUtil.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, entity.getName());
            pstmt.executeUpdate();
        }
    }

    @Override
    public User getById(int id) throws SQLException {
        String query = "SELECT * FROM \"user\" WHERE id = ?";
        User user = null;

        try (Connection conn = DBUtil.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getTimestamp("creation_time").toLocalDateTime(),
                        resultSet.getString("name")
                );
            }
        }

        return user;
    }

    @Override
    public void update(int id, User entity) throws SQLException {
        String query = "UPDATE \"user\" SET name = ? WHERE id = ?";

        try (Connection conn = DBUtil.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, entity.getName());
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deleteById(int id) throws SQLException {
        String query = "DELETE FROM \"user\" WHERE id = ?";

        try (Connection conn = DBUtil.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
