package com.librarytest.controllerTest;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.library.models.Book;
import com.library.services.BookService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:home-servlet.xml") 
@WebAppConfiguration
public class ViewControllerTest  {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
   @Mock
    private BookService bookService; 

       @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this); 
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build(); 
    }
    
    @Test
    public void checkHomePageRendered() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.view().name("home")).andExpect(MockMvcResultMatchers.forwardedUrl("/views/home.jsp"));
    }   


    @Test
    public void checkViewTableRendered() throws Exception{
      mockMvc.perform(MockMvcRequestBuilders.get("/view")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.view().name("viewbookuser")).andExpect(MockMvcResultMatchers.forwardedUrl("/views/viewbookuser.jsp")).andExpect(MockMvcResultMatchers.model().size(1)).andExpect(MockMvcResultMatchers.model().attributeExists("books"));
    }
    @Test
    public void getBooksBySearchFieldTest() throws Exception{
        List<Book> books = new LinkedList<>();
        Book book = new Book();
        books.add(book);
     
        
        when(bookService.getBooksbyName(anyString())).thenReturn(books);
        mockMvc.perform(MockMvcRequestBuilders.get("/viewbooks/to").accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

    }
    @Test
    public void getBooksBySearchFieldWithSearchTest() throws Exception{
        List<Book> books = new LinkedList<>();
        Book book = new Book();
        books.add(book);
     
        
        when(bookService.getBooksbyName(anyString())).thenReturn(books);
        mockMvc.perform(MockMvcRequestBuilders.get("/viewbooks/").accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

    }
}
