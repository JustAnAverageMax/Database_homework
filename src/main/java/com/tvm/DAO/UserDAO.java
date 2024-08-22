package com.tvm.DAO;

import com.tvm.Entity.User;
import com.tvm.utils.SessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO implements DAO<User> {

    private static final String GET_ALL_IDS = "select u.id from User u";
    private static final String GET_ALL_USERS = "from User";

    @Override
    public Class<User> getType() {
        return User.class;
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
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }

        return ids;
    }


    @Override
    public List<User> getAll(){
        List<User> users = new ArrayList<>();
        Transaction transaction = null;

        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Query<User> query = session.createQuery(GET_ALL_USERS, User.class);
            users = query.getResultList();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public Optional<User> getById(int id){
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(User.class, id));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public void update(int id, User entity){
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User existingUser = session.get(this.getType(), id);
            if (existingUser != null) {
                existingUser.setName(entity.getName());
                session.merge(existingUser);
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
