package com.librarytest;



import org.junit.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doThrow;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.library.controllers.BookController;
import com.library.models.Book;
import com.library.repositories.BookRepository;

public class BookControllerTest extends TestConfig {
    
  @Mock
    private BookRepository bookRepository;



    @InjectMocks
    private BookController bookController;



       @Test
    public void checkFormRendered() throws Exception {
        mockMvc.perform(get("/books")).andExpect(status().isOk()).andExpect(view().name("createform")).andExpect(forwardedUrl("/views/createform.jsp")).andExpect(model().size(1)).andExpect(model().attributeExists("path")).andExpect(model().attribute("path", "books"));
    }

        @Test
    public void postRequestCreateUserTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/books/add").param("name", "praveen").param("author", "testauthor").param("price", "20")).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/books")).andExpect(flash().attribute("message","Book Created Successfully" )).andExpect(flash().attribute("path", "books"));


    }

    @Test
    public void postCreateUserExceptionTest() throws Exception{
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
        
        doThrow(new RuntimeException()).when(bookRepository).insertBook(any(Book.class));
     mockMvc.perform(MockMvcRequestBuilders.post("/books/add"))
     .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/books")).andExpect(flash().attribute("message","Internal Server Error" )).andExpect(flash().attribute("path", "books"));
    }

    @Test
    public void InsertUsersIntoBookTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/addusers/insert").param("bookId", "1").param("userIds", "1","2","3")).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/addusers")).andExpect(flash().attribute("message","Users added to the book sucessfully" ));


    }

    @Test
    public void addUsersToBookExceptionTest() throws Exception{
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
        
        doThrow(new RuntimeException()).when(bookRepository).insertBook(any(Book.class));
     mockMvc.perform(MockMvcRequestBuilders.post("/addusers/insert"))
     .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/addusers")).andExpect(flash().attribute("message","Internal Server Error" ));
    }

}
