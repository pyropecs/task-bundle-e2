package com.librarytest.servicesTest;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.library.models.Book;
import com.library.repositories.BookRepository;
import com.library.services.BookService;

public class BookServiceTest {


    @InjectMocks
    private BookService bookService;

   
    @Mock
    private BookRepository bookRepository;

    private Book book1,book2;
    

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        book1 = new Book();
       
    }
    

    @Test
    public void getAllBooksTest(){
        List<Book> expectedBooks = Arrays.asList(book1,book2);
        when(bookRepository.getAllBooks()).thenReturn(expectedBooks);

        List<Book> actualBooks = bookService.getAllBooks();
        assertThat(actualBooks, is(expectedBooks));
    }

    @Test
    public void insertBookTest(){
        when(bookRepository.insertBook(book1)).thenReturn(true);
        String message = bookService.insertBook(book1);
        assertEquals("Book Created Successfully", message);

    }

    @Test
    public void insertBookFailureTest(){
        when(bookRepository.insertBook(book1)).thenReturn(false);
        String message = bookService.insertBook(book1);
        assertEquals("something went wrong.please try again later", message);

    }

    @Test
    public void insertBookWhenBookIsNull(){
        String message = bookService.insertBook(book2);
        assertEquals("no Book found",message);

    }

    @Test
    public void getBooksByNameTest(){
        List<Book> expectedBooks = Arrays.asList(book2);
        when(bookRepository.getAllBooks(anyString())).thenReturn(expectedBooks);

        List<Book> actualBooks = bookService.getBooksbyName(anyString());
        assertThat(actualBooks, is(expectedBooks));
    }

}
