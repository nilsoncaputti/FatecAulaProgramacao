package br.edu.fateclins.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    
    private static final SessionFactory sessionFactory;
    
    static{
        try{
            Configuration cfg = new Configuration().configure();
            StandardServiceRegistryBuilder builder;
            builder = new StandardServiceRegistryBuilder()
                    .applySettings(cfg.getProperties());
            sessionFactory = cfg.buildSessionFactory(builder.build());
        } catch (Throwable ex){
            ex.getMessage();
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
