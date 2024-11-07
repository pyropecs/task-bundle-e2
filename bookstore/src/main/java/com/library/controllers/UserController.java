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

import com.library.models.Book;
import com.library.models.User;
import com.library.repositories.BookRepository;
import com.library.repositories.UserRepository;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/users")
    public String createUserPage(Model model) {
        model.addAttribute("path", "users");
        return "createform";
    }

    @GetMapping("/users/all")
    public ResponseEntity<List<User>> getAllUsers() {

        try {
            List<User> users = userRepository.getAllUsers(1);

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {

            System.out.println("Exception occurred: " + e.getMessage() + " UserController.getAllUsers()");
            e.printStackTrace();

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("users/all/{bookid}")
    @ResponseBody
    public ResponseEntity<List<User>> getUsersWithBooks(@PathVariable("bookid") int bookid) {
       
        try {
            List<User> users = userRepository.getAllUsers(bookid);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage() + " UserController.getUsersWithBooks()");
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    
    }

    @PostMapping("/users/add")
    public String createUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {

        try {
            userRepository.insertUser(user);
            redirectAttributes.addFlashAttribute("message", "User Created Successfully");
            redirectAttributes.addFlashAttribute("path", "users");
            return "redirect:/users";
        } catch (Exception e) {
            System.out.println("something went wrong UserController.createUser()");
            System.out.println(e.getMessage());
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "Internal Server Error");
            redirectAttributes.addFlashAttribute("path", "users");
            return "redirect:/users";
        }
    }

    @GetMapping("/addusers")
    public String getAddUserToPage(Model model) {

        List<Book> books = bookRepository.getAllBooks();
        List<User> users = userRepository.getAllUsers();
        model.addAttribute("books", books);
        model.addAttribute("users", users);
        return "addusertobook";
    }

}
