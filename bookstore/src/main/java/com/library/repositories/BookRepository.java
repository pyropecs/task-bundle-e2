package com.library.repositories;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.library.models.Book;
import com.library.models.User;

@Repository
public class BookRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public String insertBook(Book book) {
        Session session = sessionFactory.openSession();
        
        try {
            session.save(book);
            return "book created successfully";    
        } catch (Exception e) {
            System.out.println("Exception occurred " + e.getMessage() + " BookRepository.insertBook()");
            e.printStackTrace();
            return "something went wrong book couldn't be created .please try again later";
        } finally {
            session.close();
        }

    }
    

    public String insertUsersToBook(int bookId, List<Integer> userIds) {
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
            return "users inserted into book successufully";
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println("Exception occurred " + e.getMessage() + " BookRepository.insertUsersToBook()");
            e.printStackTrace();
            return "something went wrong users couldn't be inserted.please try again later";
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
