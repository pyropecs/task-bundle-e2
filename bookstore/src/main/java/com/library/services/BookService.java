package com.library.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.models.Book;
import com.library.repositories.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    private static final Logger logger = LogManager.getLogger();
    public List<Book> getAllBooks() {
        logger.info("getting all the books from the book repository");
        List<Book> books = bookRepository.getAllBooks();
        return books;
    }

    public String insertBook(Book book) {
        String message;
        if (book != null ) {
            logger.info("inserting the book into repository Book - {}",book);
            message = bookRepository.insertBook(book) ? "Book Created Successfully" : "something went wrong.please try again later";
            logger.info("result - {}",message);
        } else {
            message = "no Book found";
            logger.error("{}",message);
        }
        return message;

    }

}
