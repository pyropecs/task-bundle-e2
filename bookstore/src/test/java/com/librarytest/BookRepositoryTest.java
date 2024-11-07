package com.librarytest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import com.library.repositories.UserRepository;

public class BookRepositoryTest {
    @Mock
    private SessionFactory sessionFactory;
    
        @Mock
    private Session session; // Mocking the Session

    @Mock
    private Transaction transaction; // Mocking the Transaction

    @Autowired
    private UserRepository userRepository;



    // @Test
    // public void GetAllBooksFromDatabaseTest()
}
