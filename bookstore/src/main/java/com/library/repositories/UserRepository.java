package com.library.repositories;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.library.models.User;

@Repository
public class UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public boolean insertUser(User user) {
        Session session = sessionFactory.openSession();
        try {
            session.save(user);
            return true;
        } catch (Exception e) {
            System.out.println("Exception occurred " + e.getMessage() + " UserRepository.insertUser()");
            
            return false;
        } finally {
            session.close();
        }

    }

    public List<User> getAllUsers(int... bookidIntegers) {
        Session session = sessionFactory.openSession();
        List<User> users = null;
        try {
            String hql = bookidIntegers.length == 0 ? "from User" : "Select u from Book b join b.users u where b.id = " + bookidIntegers[0];
            Query query = session.createQuery(hql);
            users = query.getResultList();

        } catch (Exception e) {
            System.out.println("Exception occurred " + e.getMessage() + " UserRepository.getAllUsers()");

        } finally {
            session.close();
        }
        return users;
    }

    public User getUserById(int userId) {
        Session session = sessionFactory.openSession();
        User user = session.get(User.class, userId);
        return user;
    }
}
