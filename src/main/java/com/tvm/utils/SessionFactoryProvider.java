package com.tvm.utils;

import com.tvm.Entity.Ticket;
import com.tvm.Entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class SessionFactoryProvider {
    private static SessionFactory sessionFactory;

    public SessionFactoryProvider(){}

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Ticket.class);
            StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(registryBuilder.build());
        }

        return sessionFactory;
    }
}
