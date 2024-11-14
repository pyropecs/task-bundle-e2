package com.librarytest.controllerTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.library.controllers.UserController;
import com.library.models.User;
import com.library.services.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:home-servlet.xml") 
@WebAppConfiguration
public class UserControllerTest  {
    @Autowired
    public WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;


    @Mock
    private UserService userService;



    @InjectMocks
    private UserController userController;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    private User user;
    @Test
    public void checkFormRendered() throws Exception {
        mockMvc.perform(get("/users")).andExpect(status().isOk()).andExpect(view().name("createform")).andExpect(forwardedUrl("/views/createform.jsp")).andExpect(model().size(1)).andExpect(model().attributeExists("path")).andExpect(model().attribute("path", "users"));
    }



 


    @Test
    public void postRequestCreateUserTest() throws Exception{
        when(userService.addUser(user)).thenReturn("User Created Successfully");
        mockMvc.perform(MockMvcRequestBuilders.post("/users/add").param("name", "praveen").param("department", "testdepartment").param("designation", "engine driver")).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/users")).andExpect(flash().attribute("message","User Created Successfully" )).andExpect(flash().attribute("path", "users"));
    }



}
