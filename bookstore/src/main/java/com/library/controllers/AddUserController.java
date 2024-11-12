package com.library.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.library.dto.AdduserToBookForm;
import com.library.models.Book;
import com.library.models.User;
import com.library.services.AddUserService;
import com.library.services.BookService;
import com.library.services.UserService;

@Controller
public class AddUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private AddUserService addUserService;

    @PostMapping("/addusers/insert")
    public String insertUsersToBook(@ModelAttribute AdduserToBookForm form, RedirectAttributes redirectAttributes) {
        String message = addUserService.AddUsersToBook(form);
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/addusers";
    }

    @GetMapping("addusers/all/{bookid}")
    @ResponseBody
    public ResponseEntity<List<User>> getUsersWithBook(@PathVariable("bookid") int bookid) {

        try {
            List<User> users = userService.getAllUsers(bookid);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage() + " UserController.getUsersWithBooks()");
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/addusers")
    public String getAddUserToPage(Model model) {

        List<Book> books = bookService.getAllBooks();
        List<User> users = userService.getAllUsers();
        model.addAttribute("books", books);
        model.addAttribute("users", users);
        return "addusertobook";
    }

}