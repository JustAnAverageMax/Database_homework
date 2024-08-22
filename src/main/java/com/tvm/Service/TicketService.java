package com.tvm.Service;

import com.tvm.DAO.TicketDAO;
import com.tvm.Model.Ticket;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketService {
    private final TicketDAO ticketDAO = new TicketDAO();

    public List<Integer> getAllTicketsIds(){
        List<Integer> result = new ArrayList<>();
        try{
            result = ticketDAO.getAllIds();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return result;
    }

    public List<Ticket> getAllTickets(){
        try {
            return ticketDAO.getAll();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return List.of();
    }

    public List<Ticket> getTicketsByUserId(int userId){
        try{
            return ticketDAO.getTicketsByUserId(userId);
        }catch(SQLException ex){
            ex.printStackTrace();
            return List.of();
        }
    }

    public void deleteByUserID(int userId){
        try{
            ticketDAO.deleteByUserId(userId);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public void save(Ticket ticket){
        try{
            ticketDAO.save(ticket);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public Ticket getById(int id){
        Ticket result = new Ticket();
        try{
            Optional<Ticket> ticketOpt = ticketDAO.getById(id);
            if(ticketOpt.isPresent()){
                result = ticketOpt.get();
            }else{
                System.out.println("No such ticket found");
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return result;
    }

    public void update(int id, Ticket ticket){
        try{
            ticketDAO.update(id, ticket);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public void deleteById(int id){
        try{
            ticketDAO.deleteById(id);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

}
