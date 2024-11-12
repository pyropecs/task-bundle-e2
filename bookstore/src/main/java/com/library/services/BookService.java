package com.library.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.models.Book;
import com.library.repositories.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        List<Book> books = bookRepository.getAllBooks();
        return books;   
    }

    public String insertBook(Book book) {
        String message;
        if (book != null) {
            message = bookRepository.insertBook(book);
        } else {
            message = "no Book found";
        }

        return message;

    }

}
