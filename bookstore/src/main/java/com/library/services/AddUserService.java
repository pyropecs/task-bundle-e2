package com.library.services;

import java.util.List;

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

    public String AddUsersToBook(AdduserToBookForm form) {
        String message;
        try {
            Integer bookId = form.getBookId();
            List<Integer> userIds = form.getUserIds();
            Book book = bookRepository.getBookById(bookId);
            book.getUsers().clear();
            for (Integer userId : userIds) {
                User user = userRepository.getUserById(userId);
                book.getUsers().add(user);
            }
            message = bookRepository.updateBook(book);
        } catch (Exception e) {
            System.out.println("Exception occured:" + "AddUserBookController.insertUsersToBook()");
            e.printStackTrace();
            message = "Internal Server Error";

        }
        return message;
    }
}
