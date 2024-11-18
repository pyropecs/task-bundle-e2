package com.library.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.library.models.Book;

@Repository
public class BookRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private final static Logger logger = LogManager.getLogger();

    public boolean insertBook(Book book) {
        Session session = sessionFactory.openSession();
        logger.debug("session opened");

        try {
            logger.info("saving book into the database ,Book Id - {}", book.getId());
            session.save(book);
            return true;
        } catch (Exception e) {
            logger.error("saving book into database failed.exception occurred:", e.getMessage());

            return false;
        } finally {

            session.close();
            logger.debug("session closed succcessfully");
        }

    }

    public Book getBookById(int bookId) {
        Session session = sessionFactory.openSession();
        logger.debug("session opened");
        Book book = null;
        try {
            book = session.get(Book.class, bookId);
            logger.info("recieved book from database user id - {}", bookId);
        } catch (Exception e) {
            logger.error("couldnt recieve book.exception occurred: {}", e.getMessage());

        } finally {
            logger.debug("session closed");
            session.close();
        }
      

        return book;
    }

    public List<Book> getAllBooks() {
        Session session = sessionFactory.openSession();
        logger.debug("session opened");
        List<Book> books = new ArrayList<>();
        try {
            Query query = session.createQuery("Select b from Book b");
            books = query.getResultList();
            logger.info("recieved books successfully Book size: {}", books.size());
        } catch (Exception e) {
            logger.error("couldnt recieve books.exception occurred:" + e.getMessage());
        } finally {
            session.close();
            logger.debug("session closed");
        }
        return books;
    }

    public boolean updateBook(Book book) {
        Session session = sessionFactory.openSession();
        logger.debug("session opened");
        Transaction transaction = session.beginTransaction();
        try {
            logger.debug("transaction started ");
            session.saveOrUpdate(book);
            transaction.commit();
            logger.debug("transaction committed");
            logger.info("book updated successfully");
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                logger.error("book didnt get updated.transaction got rollbacked");
            }
            logger.error("book updation failed.exception occurred: " + e.getMessage());

            return false;
        } finally {
            session.close();
            logger.debug("session closed");

        }
    }

}
