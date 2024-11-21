package com.librarytest.repositoriesTest;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.library.models.Book;
import com.library.repositories.BookRepository;
import com.library.services.AddUserService;



@RunWith(MockitoJUnitRunner.class) // Ensure proper mock initialization
public class BookRepositoryTest {

    @InjectMocks
    private BookRepository bookRepository;  // BookRepository will be injected with mocked sessionFactory

    @InjectMocks
    private AddUserService addUserService;

    @Mock
    private SessionFactory sessionFactory;  // Mock SessionFactory

    @Mock
    private Session session;  // Mock Session

    @Mock
    private Criteria criteria;  // Mock Criteria


    @Mock
    private Transaction transaction;  // Mock Transaction

    @Mock
    private Query query;

    private Book book;
    private Book book2;

    @Before
    public void setupRepo() {
        MockitoAnnotations.initMocks(this);  // Initialize mocks
        book = new Book();
        book.setId(1);
        book.setName("good book");
        book.setAuthor("good thing");
        book.setPrice(10.2f);
        book2 = new Book();
        book2.setId(1);
        book2.setName("second book");
        book2.setAuthor("author book2");
        book2.setPrice(32.4f);
       
        when(sessionFactory.openSession()).thenReturn(session);

    }

    @Test
    public void AddBookTest() {
   
        boolean isInserted = bookRepository.insertBook(book);

       
        Assert.assertEquals(true, isInserted);

    
        verify(session).save(book);
    }

    @Test
    public void AddBookExceptionTest() {
        when(session.save(any())).thenThrow(new HibernateException("something went wrong"));
        boolean isInserted = bookRepository.insertBook(book);

        Assert.assertEquals(false, isInserted);

        verify(session).save(book);
    }


    @Test
    public void getBookByIdTest(){
        when(session.get(Book.class, 1)).thenReturn(book);

        Book actualBook = bookRepository.getBookById(1);
        assertThat(actualBook, is(book));


        verify(session).close();

    }

    @Test
    public void getBookByIdExceptionTest(){
        when(session.get(Book.class, 1)).thenThrow(new HibernateException("something went wrong"));
        Book book = bookRepository.getBookById(1);
        assertNull(book);
        verify(session).close();
    }



    @Test
    public void getAllBooksTest(){
        List<Book> books = new LinkedList<>();
        books.add(book);
        books.add(book2); 
        when(session.createQuery(anyString())).thenReturn( query);
        when(query.getResultList()).thenReturn(books);

        

         books =  bookRepository.getAllBooks();
         Assert.assertArrayEquals(books.toArray(new Book[0]), books.toArray(new Book[0]));
    }
    
    @Test
    public void getAllBooksExceptionTest(){
        List<Book> books = new LinkedList<>();
        books.add(book);
        books.add(book2); 
        when(session.createQuery(anyString())).thenReturn( query);
        when(query.getResultList()).thenThrow(new RuntimeException());



         books =  bookRepository.getAllBooks();
         Assert.assertTrue(books.isEmpty());
    }


    @Test
    public void getAllBooksWithSearchStringTest(){
        String matcher = "to";
        String expectedHql = "select b from Book b where b.name like :name";
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.createQuery(expectedHql)).thenReturn(query);
        when(query.getResultList()).thenReturn(Arrays.asList(new Book(), new Book())); // Replace with actual User objects as needed
        List<Book> result = bookRepository.getAllBooks(matcher);
        verify(session).createQuery(expectedHql);
        verify(session).close();
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void updateBookTest(){
        when(session.beginTransaction()).thenReturn(transaction);
        
        boolean isUpdated = bookRepository.updateBook(book);
        assertTrue(isUpdated);
        verify(transaction).commit();
        verify(session).saveOrUpdate(book);
        verify(session).close();
    }

    @Test
    public void updateBookExceptionTest(){
        when(session.beginTransaction()).thenReturn(transaction);
        doThrow(new HibernateException("something went wrong")).when(session).saveOrUpdate(book);
        boolean isUpdated = bookRepository.updateBook(book);
        assertFalse(isUpdated);
        verify(transaction).rollback();
        verify(session).close();
    }
    @Test
    public void updateBookExceptionTransactionNullTest(){
        when(session.beginTransaction()).thenReturn(null);
        doThrow(new HibernateException("something went wrong")).when(session).saveOrUpdate(book);
        boolean isUpdated = bookRepository.updateBook(book);
        assertFalse(isUpdated);
        verify(transaction,never()).rollback();
        verify(session).close();
    }
}
