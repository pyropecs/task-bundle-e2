package com.library.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

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
    
    @GetMapping("/viewbooks/{search}")
    @ResponseBody
    public ResponseEntity<List<Book>> getBooksBySearchField(@PathVariable("search") String searchString) {

        logger.info("Client requested books with search string");
        List<Book> books = bookService.getBooksbyName(searchString);
        logger.info("Recieved books with searchString - {}", searchString);

        return new ResponseEntity<>(books, HttpStatus.OK);
    }


    @GetMapping("/viewbooks/")
    @ResponseBody
    public ResponseEntity<List<Book>> getBooksBySearchField() {

        logger.info("Client requested all books via rest api ");
        List<Book> books = bookService.getBooksbyName();
        logger.info("Recieved all Books via rest api - {}",books);

        return new ResponseEntity<>(books, HttpStatus.OK);

    }

}
