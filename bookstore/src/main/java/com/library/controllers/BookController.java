package com.library.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.library.models.Book;
import com.library.services.BookService;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;
    private static final Logger logger = LogManager.getLogger();

    @GetMapping("/books")
    public String getCreateBookPage(Model model) {
        logger.info("Recieved /books request and rendering create form as path book");
        model.addAttribute("path", "books");
        return "createform";
    }

    @PostMapping("/books/add")
    public String postBook( @ModelAttribute Book book, RedirectAttributes redirectAttributes) {
        String message;
        logger.info("Client adding book Object - {} ",book);
        message = bookService.insertBook(book);
        logger.info("user object added successfully user - {}",book);
        redirectAttributes.addFlashAttribute("message", message);
        redirectAttributes.addFlashAttribute("path", "books");
        return "redirect:/books";
    }
}
