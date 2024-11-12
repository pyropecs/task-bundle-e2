package com.library.controllers;

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

    @GetMapping("/books")
    public String getCreateBookPage(Model model) {
        model.addAttribute("path", "books");
        return "createform";
    }

    @PostMapping("/books/add")
    public String postBook(@ModelAttribute Book book, RedirectAttributes redirectAttributes) {
        String message;
        message = bookService.insertBook(book);
        redirectAttributes.addFlashAttribute("message", message);
        redirectAttributes.addFlashAttribute("path", "books");
        return "redirect:/books";
    }
}
