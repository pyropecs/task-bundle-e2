package com.librarytest.controllerTest;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.library.controllers.AddUserController;
import com.library.dto.AdduserToBookForm;
import com.library.models.User;
import com.library.services.AddUserService;
import com.library.services.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:home-servlet.xml") 
@WebAppConfiguration
public class AddUserControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private AddUserService addUserService; 

    @InjectMocks
    private AddUserController addUserController;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this); 
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build(); 
    }

        @Test
    public void insertUsersIntoBookTest() throws Exception{
        when(addUserService.AddUsersToBook(any(AdduserToBookForm.class))).thenReturn("Users Updated into Book Successufully");
        mockMvc.perform(post("/addusers/insert").param("bookId", "1").param("userIds", "1","2","3")).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/addusers")).andExpect(flash().attribute("message","Users Updated into Book Successufully" ));


    }

    @Test
    public void getUsersWithBookTest() throws Exception{
                List<User> users = new LinkedList<>();
        User user1 = new User();
        user1.setId(1);
        user1.setName("praveen");
        user1.setDepartment("electrical");
        user1.setDesignation("engineer");
        users.add(user1);
        
        when(userService.getAllUsers(1)).thenReturn(users);
        mockMvc.perform( get("/addusers/all/1").accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }    
  @Test
    public void getAddUserToPageFormTest() throws Exception{
        mockMvc.perform(get("/addusers")).andExpect(status().isOk()).andExpect(view().name("addusertobook")).andExpect(forwardedUrl("/views/addusertobook.jsp"));


    }

    

}
