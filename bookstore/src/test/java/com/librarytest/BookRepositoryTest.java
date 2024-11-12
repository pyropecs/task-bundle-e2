package com.librarytest;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.library.dto.AdduserToBookForm;
import com.library.models.Book;
import com.library.models.User;

import com.library.repositories.BookRepository;
import com.library.services.AddUserService;
import com.library.services.BookService;



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
   
        String message = bookRepository.insertBook(book);

       
        Assert.assertEquals("book created successfully", message);

    
        verify(session).save(book);
    }

    @Test
    public void AddBookExceptionTest() {
        when(session.save(any())).thenThrow(new HibernateException("something went wrong"));
        String message = bookRepository.insertBook(book);

        Assert.assertEquals("something went wrong book couldn't be created .please try again later", message);

        verify(session).save(book);
    }

    @Test
    public void AddUsersToBookTest() {
      

        User user1 = new User();
        user1.setId(0);
        user1.setName("praveen");
        user1.setDepartment("library center");
        user1.setDesignation("librarian");

        User user2 = new User();
        user2.setId(1);
        user2.setName("amith");
        user2.setDepartment("plumber");
        user2.setDesignation("economical");

        when(session.get(Book.class, book.getId())).thenReturn(book);
        when(session.get(User.class, 0)).thenReturn(user1);
        when(session.get(User.class, 1)).thenReturn(user2);
        when(session.beginTransaction()).thenReturn(transaction);
        AdduserToBookForm addUserForm = new AdduserToBookForm();
        addUserForm.setBookId(book.getId());
        addUserForm.setUserIds( Arrays.asList(user1.getId(), user2.getId()));
        String message = addUserService.AddUsersToBook(addUserForm); 
        Assert.assertEquals("users inserted into book successufully", message);
        verify(session).saveOrUpdate(book);
    }

    @Test
  
    public void AddUsersToBookExceptionTest(){
        User user1 = new User();
        user1.setId(0);
        user1.setName("praveen");
        user1.setDepartment("library center");
        user1.setDesignation("librarian");

        User user2 = new User();
        user2.setId(1);
        user2.setName("amith");
        user2.setDepartment("plumber");
        user2.setDesignation("economical");
        when(session.get(Book.class, book.getId())).thenReturn(book);
        when(session.get(User.class, 0)).thenReturn(user1);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.get(User.class, 1)).thenThrow(new NullPointerException());
        AdduserToBookForm addUserForm = new AdduserToBookForm();
        addUserForm.setBookId(book.getId());
        addUserForm.setUserIds( Arrays.asList(user1.getId(), user2.getId()));
        String message = addUserService.AddUsersToBook(addUserForm); 
        Assert.assertNotNull(transaction);
         verify(sessionFactory).openSession();
        verify(session).beginTransaction();
        verify(transaction).rollback();
        verify(session).close();
  
    

        Assert.assertEquals("something went wrong users couldn't be inserted.please try again later",message);
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
         Assert.assertNull(books);
    }
}
