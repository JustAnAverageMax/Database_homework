package com.tvm.Service;

import com.tvm.DAO.TicketAndUserDAO;
import com.tvm.Entity.Ticket;
import com.tvm.Entity.User;

import java.sql.SQLException;

public class TicketAndUserService {
    private final TicketAndUserDAO ticketAndUserDAO = new TicketAndUserDAO();

    public void saveUserAndTicket(User user, Ticket ticket, int userId, int ticketId) {
        ticketAndUserDAO.updateUserAndTicket(user, ticket, userId, ticketId);
    }
}
