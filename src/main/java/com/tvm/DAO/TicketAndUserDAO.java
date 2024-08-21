package com.tvm.DAO;

import com.tvm.Entity.Ticket;
import com.tvm.Entity.User;
import com.tvm.utils.SessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TicketAndUserDAO {

    public void updateUserAndTicket(User user, Ticket ticket, int userId, int ticketId) {
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            User existingUser = session.get(User.class, userId);
            if (existingUser != null) {
                existingUser.setName(user.getName());
                session.merge(existingUser);
            }
            Ticket existingTicket = session.get(Ticket.class, ticketId);
            if (existingTicket != null) {
                existingTicket.setTicketType(ticket.getTicketType());
                existingTicket.setUserId(ticket.getUserId());
            }
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }
}
