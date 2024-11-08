package com.customers.common;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.customers.beanclasses.Customer;

public class HibernateInit {

public Session init(){
        Configuration cfg = new Configuration().configure();
        SessionFactory sessionFactoryInstance = cfg.buildSessionFactory();
        Session session = sessionFactoryInstance.openSession();        
      
        return session;
}



}
