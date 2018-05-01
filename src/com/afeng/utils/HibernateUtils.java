package com.afeng.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static final Configuration configuration;
    private static final SessionFactory sessionFactory;
    static {
        configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    /**
     * 开启一个新的会话
     * @return 开启的新会话
     */
    public static Session openSession(){
        return sessionFactory.openSession();
    }

    /**
     * 获得当前的与ThreadLocal绑定的会话
     * @return 当前会话
     */
    public static Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

}
