package com.tvm.DAO;

import com.tvm.Model.Ticket;
import com.tvm.Model.User;
import com.tvm.constants.Queries;
import com.tvm.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TicketAndUserDAO {

    public void updateUserAndTicket(User user, Ticket ticket, int userId, int ticketId) throws SQLException {
        String userQuery = Queries.UPDATE_USER;
        String ticketQuery = Queries.UPDATE_TICKET;

        try (Connection conn = DBUtil.connect()) {
            conn.setAutoCommit(false);
            try(PreparedStatement userPStatement = conn.prepareStatement(userQuery);
                PreparedStatement ticketPStatement = conn.prepareStatement(ticketQuery)){

                userPStatement.setString(1, user.getName());
                userPStatement.setInt(2, userId);
                userPStatement.executeUpdate();

                ticketPStatement.setString(1, ticket.getTicketType().toString());
                ticketPStatement.setInt(2, ticket.getUserId());
                ticketPStatement.setInt(3, ticketId);
                ticketPStatement.executeUpdate();

                conn.commit();
            }catch(SQLException ex){
                ex.printStackTrace();
                conn.rollback();
            }
        }
    }
}
