package com.librarytest.servicesTest;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.anyInt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.library.dto.AdduserToBookForm;
import com.library.models.Book;
import com.library.models.User;
import com.library.repositories.BookRepository;
import com.library.repositories.UserRepository;
import com.library.services.AddUserService;

public class AddUserServiceTest {
    
    @InjectMocks
    private AddUserService addUserService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserRepository userRepository;

    private AdduserToBookForm form;
    private Book book;
    private User user1;
    private User user2;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        book = new Book();
        user1 = new User();
        user2 = new User();
        form = new AdduserToBookForm();
        form.setBookId(1);
       form.setUserIds(Arrays.asList(1,2));
    
    }
    @Test
    public void AddUsersToBookTest(){
        
        when(bookRepository.getBookById(anyInt())).thenReturn(book);
        when(userRepository.getUserById(1)).thenReturn(user1);
        when(userRepository.getUserById(2)).thenReturn(user2);
        when(bookRepository.updateBook(book)).thenReturn(true);
      
        String message = addUserService.AddUsersToBook(form);
        assertEquals("Book Id: 1User Ids:[1, 2]",form.toString());
        assertEquals("Users Updated into Book Successufully", message);
        
        verify(userRepository,times(2)).getUserById(anyInt());
        verify(bookRepository,times(1)).getBookById(anyInt());
    }
    
    @Test
    public void AddUsersToBookWithNoUserIds(){
        form.setUserIds(null);
        when(bookRepository.getBookById(anyInt())).thenReturn(book);
        when(bookRepository.updateBook(book)).thenReturn(true);
        String message = addUserService.AddUsersToBook(form);
        assertEquals("Users Updated into Book Successufully", message);
        
        verify(userRepository,times(0)).getUserById(anyInt());
        verify(bookRepository,times(1)).getBookById(anyInt());
    }

    @Test
    public void AddUsersToBookFailedTest(){
      
        when(bookRepository.getBookById(anyInt())).thenReturn(book);
        when(bookRepository.updateBook(book)).thenReturn(false);
        String message = addUserService.AddUsersToBook(form);
        assertEquals("something went wrong.please try again later", message);
        
        verify(userRepository,times(2)).getUserById(anyInt());
        verify(bookRepository,times(1)).getBookById(anyInt());
    }


}
