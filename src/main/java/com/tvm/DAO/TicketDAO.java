package com.tvm.DAO;

import com.tvm.Model.Ticket;
import com.tvm.constants.Queries;
import com.tvm.constants.TicketType;
import com.tvm.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketDAO implements DAO<Ticket>{

    public List<Ticket> getTicketsByUserId(int userId)throws SQLException{
        String query = Queries.GET_TICKETS_BY_USER_ID;
        List<Ticket> tickets = new ArrayList<>();

        try(Connection conn = DBUtil.connect(); PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setLong(1, userId);
            ResultSet resultSet = pstmt.executeQuery();
            while(resultSet.next()){
                Ticket ticket = new Ticket(
                        resultSet.getTimestamp("creation_date").toLocalDateTime(),
                        ((TicketType) resultSet.getObject("ticket_type")),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("id")
                );
                tickets.add(ticket);
            }
        }

        return tickets;
    }

    public void deleteByUserId(int userId) throws SQLException{
        String query = Queries.DELETE_TICKET_BY_USER_ID;

        try(Connection conn = DBUtil.connect(); PreparedStatement pstmp = conn.prepareStatement(query)){
            pstmp.setLong(1, userId);
            pstmp.executeUpdate();
        }
    }

    @Override
    public List<Ticket> getAll() throws SQLException {
        String query = Queries.GET_ALL_TICKETS;
        List<Ticket> tickets = new ArrayList<>();

        try(Connection conn = DBUtil.connect(); Statement statement = conn.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                Ticket ticket = new Ticket(
                        resultSet.getTimestamp("creation_date").toLocalDateTime(),
                        TicketType.valueOf(resultSet.getString("ticket_type")),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("id")
                );
                tickets.add(ticket);
            }
        }
        return tickets;
    }

    @Override
    public void save(Ticket entity) throws SQLException {
        String query = Queries.SAVE_TICKET;

        try (Connection conn = DBUtil.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setObject(1, entity.getTicketType().toString());
            pstmt.setLong(2, entity.getUserId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public Optional<Ticket> getById(int id) throws SQLException {
        String query = Queries.GET_TICKET_BY_ID;
        Optional<Ticket> ticket = Optional.empty();

        try (Connection conn = DBUtil.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                ticket = Optional.of(new Ticket(
                        resultSet.getTimestamp("creation_date").toLocalDateTime(),
                        TicketType.valueOf(resultSet.getString("ticket_type")),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("id")
                ));
            }
        }

        return ticket;
    }

    @Override
    public void update(int id, Ticket entity) throws SQLException {
        String query = Queries.UPDATE_TICKET_TYPE;

        try (Connection conn = DBUtil.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setObject(1, entity.getTicketType());
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deleteById(int id) throws SQLException {
        String query = Queries.DELETE_TICKET_BY_ID;

        try (Connection conn = DBUtil.connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
