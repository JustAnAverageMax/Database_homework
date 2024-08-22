package com.tvm.Service;

import com.tvm.DAO.TicketAndUserDAO;
import com.tvm.Model.Ticket;
import com.tvm.Model.User;

import java.sql.SQLException;

public class TicketAndUserService {
    private final TicketAndUserDAO ticketAndUserDAO = new TicketAndUserDAO();

    public void saveUserAndTicket(User user, Ticket ticket, int userId, int ticketId){
        try{
            ticketAndUserDAO.updateUserAndTicket(user, ticket, userId, ticketId);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
