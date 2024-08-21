package com.tvm.DAO;

import com.tvm.utils.SessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    Class<T> getType();

    List<Integer> getAllIds() ;
    List<T> getAll();
    void update(int id, T entity);

    default Optional<T> getById(int id){
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(getType(), id));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    default void deleteById(int id){
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            T entity = session.get(getType(), id);
            if (entity != null) {
                session.remove(entity);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    default void save(T entity){
        Transaction transaction = null;
        try (Session session = SessionFactoryProvider.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
