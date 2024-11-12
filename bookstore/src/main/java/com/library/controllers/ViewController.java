package com.library.controllers;

import java.util.List;

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
   

    @GetMapping("/")
    public String renderHomePage() {
        return "home";
    }

    @GetMapping("/view")
    public String viewBooksUsers(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "viewbookuser";

    }

}
