package com.library.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.dto.AdduserToBookForm;
import com.library.models.Book;
import com.library.models.User;
import com.library.repositories.BookRepository;
import com.library.repositories.UserRepository;

@Service
public class AddUserService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;
    private static final Logger logger = LogManager.getLogger();
    public String AddUsersToBook(AdduserToBookForm form) {
        
        Integer bookId = form.getBookId();
        List<Integer> userIds = form.getUserIds();
        logger.info("Adding users to book. Book ID: {}, User IDs: {}", bookId, userIds);
        Book book = bookRepository.getBookById(bookId);
        book.getUsers().clear();
        if (userIds != null) {
            for (Integer userId : userIds) {
                User user = userRepository.getUserById(userId);
                book.getUsers().add(user);
                logger.info("Added User with ID {} to Book ID {}", userId, bookId);
            }
            
        }
        String message = bookRepository.updateBook(book) ? "Users Updated into Book Successufully" : "something went wrong.please try again later";
        logger.info("result - {}",message);
        
        return message;
    }

   
}
