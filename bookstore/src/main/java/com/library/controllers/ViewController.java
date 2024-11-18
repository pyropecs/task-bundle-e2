package com.library.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.library.models.Book;
import com.library.services.BookService;

@Controller
public class ViewController {

    @Autowired
    private BookService bookService;

    private static final Logger logger = LogManager.getLogger();

    @GetMapping("/")
    public String renderHomePage() {
        logger.info("Recieved /home request to fetch the homepage");

        return "home";
    }

    @GetMapping("/view")
    public String viewBooksUsers(Model model) {

        logger.info("Recieved /view request to fetch the viewbookuser page");
        List<Book> books = bookService.getAllBooks();
        logger.info("Recieved Books from bookService.getAllBooks() - {}", books);
        
        model.addAttribute("books", books);

        return "viewbookuser";
    }

}
