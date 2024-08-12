package com.tvm.DAO;

import com.tvm.Model.Ticket;
import com.tvm.constants.TicketType;
import com.tvm.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO implements DAO<Ticket>{

    public List<Ticket> getTicketsByUserId(int userId )throws SQLException{
        String query = "SELECT * FROM ticket WHERE user_id = ?";
        List<Ticket> tickets = new ArrayList<>();

        try(Connection conn = DBUtil.connect(); PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setLong(1, userId);
            ResultSet resultSet = pstmt.executeQuery();
            while(resultSet.next()){
                Ticket ticket = new Ticket(
                        resultSet.getTimestamp("creation_date").toLocalDateTime(),
                        ((TicketType) resultSet.getObject("ticket_type")),
                        resultSet.getLong("user_id"),
                        resultSet.getLong("id")
                );
                tickets.add(ticket);
            }
        }

        return tickets;
    }

    public void deleteByUserId(int userId) throws SQLException{
        String query = "DELETE FROM ticket WHERE user_id = ?";

        try(Connection conn = DBUtil.connect(); PreparedStatement pstmp = conn.prepareStatement(query)){
            pstmp.setLong(1, userId);
            pstmp.executeUpdate();
        }
    }

    @Override
    public void save(Ticket entity) throws SQLException {
        String query = "INSERT INTO ticket (ticket_type, user_id) VALUES (?::ticket_type, ?)";

        try (Connection conn = DBUtil.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setObject(1, entity.getTicketType().toString());
            pstmt.setLong(2, entity.getUserId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public Ticket getById(int id) throws SQLException {
        String query = "SELECT * FROM ticket WHERE id = ?";
        Ticket ticket = null;

        try (Connection conn = DBUtil.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                ticket = new Ticket(
                        resultSet.getTimestamp("creation_time").toLocalDateTime(),
                        resultSet.getObject("ticket_type", TicketType.class),
                        resultSet.getLong("user_id"),
                        resultSet.getLong("id")
                );
            }
        }

        return ticket;
    }

    @Override
    public void update(int id, Ticket entity) throws SQLException {
        String query = "UPDATE ticket SET ticket_type = ? WHERE id = ?";

        try (Connection conn = DBUtil.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setObject(1, entity.getTicketType());
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deleteById(int id) throws SQLException {
        String query = "DELETE FROM ticket WHERE id = ?";

        try (Connection conn = DBUtil.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
