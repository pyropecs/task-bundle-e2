package com.librarytest.repositoriesTest;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

import com.library.models.User;
import com.library.repositories.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryTest {

    @InjectMocks
    private UserRepository userRepository;
    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @Mock
    private Query query;

    private User user;
    private User user2;

    @Before
    public void setup() {
        user = new User();
        user.setId(1);
        user.setName("ashok amban");
        user.setDepartment("law");
        user.setDesignation("lawyer");
        when(sessionFactory.openSession()).thenReturn(session);
    }

    @Test
    public void AddUserTest() {

        boolean isInserted = userRepository.insertUser(user);
        Assert.assertEquals(true, isInserted);
        verify(session).save(user);
        verify(session).close();
    }

    @Test
    public void AddUserExceptionTest() {
        when(session.save(any())).thenThrow(new HibernateException("something went wrong"));
        boolean isInserted = userRepository.insertUser(user);
        Assert.assertEquals(false, isInserted);
        verify(session).save(user);
        verify(session).close();
    }

    @Test
    public void getAllUsersTest() {
        List<User> users = new LinkedList<>();
        users.add(user);
        users.add(user2);
        String expectedHql = "from User";
        when(session.createQuery(anyString())).thenReturn(query);
        when(query.getResultList()).thenReturn(users);
        users = userRepository.getAllUsers();
        verify(session).createQuery(expectedHql);
        Assert.assertArrayEquals(users.toArray(new User[0]), users.toArray(new User[0]));
    }

    @Test
    public void getAllUsersExceptionTest() {
        List<User> users = new LinkedList<>();
        users.add(user);
        users.add(user2);
        when(session.createQuery(anyString())).thenReturn(query);
        when(query.getResultList()).thenThrow(new RuntimeException());
        users = userRepository.getAllUsers();
        Assert.assertNull(users);
    }

    @Test
    public void testGetAllUsersWithBookIds() {

        int[] bookidIntegers = {1};
        String expectedHql = "Select u from Book b join b.users u where b.id = 1";
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.createQuery(expectedHql)).thenReturn(query);
        when(query.getResultList()).thenReturn(Arrays.asList(new User(), new User())); // Replace with actual User objects as needed
        List<User> result = userRepository.getAllUsers(bookidIntegers);
        verify(session).createQuery(expectedHql);
        verify(session).close();
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void getUserByIdTest(){
        when(session.get(User.class,1)).thenReturn(user);
        User actualUser = userRepository.getUserById(1);
        assertThat(user, is(actualUser));
    }

    @Test
    public void getUserByIdExceptionTest(){
        when(session.get(User.class,1)).thenThrow(new HibernateException("something went wrong "));
         user = userRepository.getUserById(1);
        assertNull(user);
    }
}
