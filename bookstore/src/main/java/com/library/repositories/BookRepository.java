package com.library.repositories;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import com.library.models.Book;
import com.library.models.User;

@Repository
public class BookRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public void insertBook(Book book) {
        Session session = sessionFactory.openSession();
        try {
            session.save(book);
        } catch (Exception e) {
            System.out.println("Exception occurred " + e.getMessage() + " BookRepository.insertBook()");
            e.printStackTrace();

        } finally {
            session.close();
        }

    }
    

    public void insertUsersToBook(int bookId, List<Integer> userIds) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Book book = session.get(Book.class, bookId);
            book.getUsers().clear();
            for (Integer userId : userIds) {
                User user = session.get(User.class, userId);
                book.getUsers().add(user);
                user.getBooks().add(book); 
            }
            session.saveOrUpdate(book);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println("Exception occurred " + e.getMessage() + " BookRepository.insertUsersToBook()");
            e.printStackTrace();
        } finally {
            session.close();
        
        }
    }


    public List<Book> getAllBooks() {
        Session session = sessionFactory.openSession();
        List<Book> books = null;
        try {
            
            Query query = session.createQuery("Select b from Book b");
            books = query.getResultList();
        } catch (Exception e) {
            System.out.println("Exception occurred " + e.getMessage() + " BookRepository.getAllBooks()");
            e.printStackTrace();
        } finally {
            session.close();
        }
        return books;
    }

}
