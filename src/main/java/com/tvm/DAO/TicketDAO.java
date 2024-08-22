package com.tvm.DAO;

import com.tvm.Entity.Ticket;
import com.tvm.constants.Queries;
import com.tvm.constants.TicketType;
import com.tvm.utils.SessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketDAO implements DAO<Ticket> {

    private static final String GET_ALL_TICKETS = "from Ticket";
    private static final String GET_ALL_IDS = "select t.id from Ticket t";
    private static final String DELETE_BY_USER_ID = "delete from Ticket t where t.userId = :userId";
    private static final String GET_TICKETS_BY_USER_ID = "from Ticket t where t.userId = :userId";

    public List<Ticket> getTicketsByUserId(int userId){
        List<Ticket> tickets = new ArrayList<>();
        Transaction transaction = null;
        try(Session session = SessionFactoryProvider.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            Query<Ticket> query = session.createQuery(GET_TICKETS_BY_USER_ID, Ticket.class);
            query.setParameter("userId", userId);
            tickets = query.getResultList();
            transaction.commit();
        }catch (Exception ex){
            ex.printStackTrace();
            if(transaction!=null){
                transaction.rollback();
            }
        }

        return tickets;
    }

    public void deleteByUserId(int userId){
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            MutationQuery query = session.createMutationQuery(DELETE_BY_USER_ID);
            query.setParameter("userId", userId);
            int result = query.executeUpdate();
            System.out.println("Number of records deleted = " + result);
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    @Override
    public Class<Ticket> getType() {
        return Ticket.class;
    }

    @Override
    public List<Integer> getAllIds(){
        List<Integer> ids = new ArrayList<>();
        Transaction transaction = null;

        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Query<Integer> query = session.createQuery(GET_ALL_IDS, Integer.class);
            ids = query.getResultList();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return ids;
    }

    @Override
    public List<Ticket> getAll(){
        List<Ticket> tickets = new ArrayList<>();
        Transaction transaction = null;

        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Query<Ticket> query = session.createQuery(GET_ALL_TICKETS, Ticket.class);
            tickets = query.getResultList();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return tickets;
    }

    @Override
    public void update(int id, Ticket entity){
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Ticket existingTicket = session.get(this.getType(), id);
            if (existingTicket != null) {
                existingTicket.setTicketType(entity.getTicketType());
                session.merge(existingTicket);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
