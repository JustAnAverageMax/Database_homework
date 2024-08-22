package com.tvm.Service;

import com.tvm.DAO.TicketDAO;
import com.tvm.Entity.Ticket;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketService {
    private final TicketDAO ticketDAO = new TicketDAO();

    public List<Integer> getAllTicketsIds() {
        return ticketDAO.getAllIds();
    }

    public List<Ticket> getAllTickets() {
        return ticketDAO.getAll();
    }

    public List<Ticket> getTicketsByUserId(int userId) {
        return ticketDAO.getTicketsByUserId(userId);
    }

    public void deleteByUserID(int userId) {
        ticketDAO.deleteByUserId(userId);
    }

    public void save(Ticket ticket) {
        ticketDAO.save(ticket);
    }

    public Ticket getById(int id) {
        Ticket result = new Ticket();

        Optional<Ticket> ticketOpt = ticketDAO.getById(id);
        if (ticketOpt.isPresent()) {
            result = ticketOpt.get();
        } else {
            System.out.println("No such ticket found");
        }

        return result;
    }

    public void update(int id, Ticket ticket) {
        ticketDAO.update(id, ticket);
    }

    public void deleteById(int id) {
        ticketDAO.deleteById(id);
    }

}
