package org.example.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory session = new Configuration().configure().buildSessionFactory();

    public static SessionFactory getSession(){
        return session;
    }

}
