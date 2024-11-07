package com.library.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.library.models.Book;

import com.library.repositories.BookRepository;

@Controller
public class ViewController {

    @Autowired
    private BookRepository repository;

    @GetMapping("/")
    public String renderHomePage() {
        return "home";
    }

    @GetMapping("/view")
    public String viewBooks(Model model) {
        List<Book> books = repository.getAllBooks();

        model.addAttribute("books", books);
        return "viewbookuser";

    }

}
