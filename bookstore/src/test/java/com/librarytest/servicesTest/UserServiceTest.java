package com.librarytest.servicesTest;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.library.models.User;
import com.library.repositories.UserRepository;
import com.library.services.UserService;

public class UserServiceTest {
      @InjectMocks
    private UserService userService;

   
    @Mock
    private UserRepository userRepository;

    private User user1,user2;
    

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        user1 = new User();
       
    }
    

    @Test
    public void getAllUsersTest(){
        List<User> expectedUsers = Arrays.asList(user1,user2);
        when(userRepository.getAllUsers(1)).thenReturn(expectedUsers);

        List<User> actualUsers = userService.getAllUsers(1);
        assertThat(actualUsers, is(expectedUsers));
    }

    @Test
    public void insertUserTest(){
        when(userRepository.insertUser(user1)).thenReturn(true);
        String message = userService.addUser(user1);
        assertEquals("User Created Successfully", message);

    }

    @Test
    public void insertUserFailureTest(){
        when(userRepository.insertUser(user1)).thenReturn(false);
        String message = userService.addUser(user1);
        assertEquals("something went wrong.please try again later", message);

    }

    @Test
    public void insertUserWhenUserIsNull(){
        String message = userService.addUser(user2);
        assertEquals("User not found",message);

    }
}
