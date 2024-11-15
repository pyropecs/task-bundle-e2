package com.library.repositories;

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
        logger.info("session opened");
        
        try {
            logger.info("saving book into the database ,Book Id - {}",book.getId());
            session.save(book);
            return true; 
        } catch (Exception e) {
            logger.error("saving book into database failed.exception occurred:",e.getMessage());
          
            return false; 
        } finally {
           
            session.close();
            logger.info("session closed succcessfully");
        }

    }

    public Book getBookById(int bookId) {
        Session session = sessionFactory.openSession();
        logger.info("session opened");
        Book book = null;
        try {
            book = session.get(Book.class, bookId); 
            logger.info("recieved book from database user id - {}",bookId);
        } catch (Exception e) {
            logger.error("couldnt recieve book.exception occurred: {}",e.getMessage());

        }finally{
            logger.info("session closed");
            session.close();
        }
       
        return book;
    }

    public List<Book> getAllBooks() {
        Session session = sessionFactory.openSession();
        logger.info("session opened");
        List<Book> books = null;
        try {
            Query query = session.createQuery("Select b from Book b");
            books = query.getResultList();
            logger.info("recieved books successfully Book size: {}",books.size());
        } catch (Exception e) {

            logger.error("couldnt recieve books.exception occurred:"+e.getMessage());
        } finally {
            session.close();
            logger.info("session closed");
        }
        return books;
    }

    public boolean updateBook(Book book) {
        Session session = sessionFactory.openSession();
        logger.info("session opened");
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            logger.info("transaction started ");
            session.saveOrUpdate(book);
            transaction.commit();
            logger.info("transaction committed and book updated successfully");
            return true; 
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                logger.warn("book didnt get updated.transaction got rollbacked");
            }
            logger.error("book updation failed.exception occurred: "+e.getMessage());
          
            return false; 
        } finally {
            logger.info("session closed");
            session.close();

        }
    }

}
