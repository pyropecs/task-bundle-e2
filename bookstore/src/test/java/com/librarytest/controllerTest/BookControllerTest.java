package com.librarytest.controllerTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.library.controllers.BookController;
import com.library.models.Book;
import com.library.services.BookService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:home-servlet.xml") 
@WebAppConfiguration
public class BookControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Mock
    private BookService bookService; 

    @InjectMocks
    private BookController bookController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this); 
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build(); 
    }
    @Test
    public void checkFormRendered() throws Exception {
        mockMvc.perform(get("/books")).andExpect(status().isOk()).andExpect(view().name("createform")).andExpect(forwardedUrl("/views/createform.jsp")).andExpect(model().size(1)).andExpect(model().attributeExists("path")).andExpect(model().attribute("path", "books"));
    }


    @Test
    public void postRequestCreateUserTest() throws Exception {
        when(bookService.insertBook(any(Book.class))).thenReturn("Book Created Successfully");

        mockMvc.perform(post("/books")
                .param("name", "apocalypse")
                .param("author", "testauthor")
                .param("price", "20"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"))
                .andExpect(flash().attribute("message", "Book Created Successfully"));
    }
}
