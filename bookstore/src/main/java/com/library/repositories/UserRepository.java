package com.library.repositories;

import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.library.models.User;

@Repository
public class UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private final static Logger logger = LogManager.getLogger();
    public boolean insertUser(User user) {
        
        Session session = sessionFactory.openSession();
        logger.info("session opened");
        try {
            logger.info("saving user into the database ,User Id - {}",user.getId());
            session.save(user);
            return true;
        } catch (Exception e) {
            logger.error("saving user into database failed.exception occurred:",e.getMessage());
            return false;
        } finally {
            session.close();
            logger.info("session closed successfully");
        }

    }

    public List<User> getAllUsers(int... bookidIntegers) {
        logger.info("getting all users with book id - {}",bookidIntegers);
        Session session = sessionFactory.openSession();
        logger.info("session opened");
        List<User> users = null;
        try {
            String hql = bookidIntegers.length == 0 ? "from User" : "Select u from Book b join b.users u where b.id = " + bookidIntegers[0];
            Query query = session.createQuery(hql);
            users = query.getResultList();
            logger.info("successfully recieved users",users.size());

        } catch (Exception e) {
            logger.error("cant get every users.exception occurrred: {} ",e.getMessage());

        } finally {
            session.close();
            logger.info("session closed");
        }
        return users;
    }

    public User getUserById(int userId) {
        Session session = sessionFactory.openSession();
        logger.info("session opened");
        User user = session.get(User.class, userId);
        logger.info("successfully recieved from database user with id - {}",user.getId());
        return user;
    }
}
