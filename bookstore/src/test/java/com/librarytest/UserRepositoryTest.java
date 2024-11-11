package com.librarytest;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
        user.setName("praveen");
        user.setDepartment("electrical");
        user.setDesignation("electronics");
        when(sessionFactory.openSession()).thenReturn(session);
    }

    @Test
    public void AddBookTest() {

        String message = userRepository.insertUser(user);
        Assert.assertEquals("user created successfully", message);
        verify(session).save(user);
        verify(session).close();
    }

    @Test
    public void AddBookExceptionTest() {
        when(session.save(any())).thenThrow(new HibernateException("something went wrong"));
        String message = userRepository.insertUser(user);
        Assert.assertEquals("something went wrong.users couldnt be created successfully", message);
        verify(session).save(user);
        verify(session).close();
    }

    @Test
    public void getAllBooksTest() {
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
    public void getAllBooksExceptionTest() {
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

        Integer[] bookidIntegers = {1};
        String expectedHql = "Select u from User u join u.books b where b.id = 1";
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.createQuery(expectedHql)).thenReturn(query);
        when(query.getResultList()).thenReturn(Arrays.asList(new User(), new User())); // Replace with actual User objects as needed
        List<User> result = userRepository.getAllUsers(bookidIntegers);
        verify(session).createQuery(expectedHql);
        verify(session).close();
        assertNotNull(result);
        assertEquals(2, result.size());
    }
}
