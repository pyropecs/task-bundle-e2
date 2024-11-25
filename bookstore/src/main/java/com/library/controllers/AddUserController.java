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

    private static final Logger logger = LogManager.getLogger();


    @GetMapping("/addusers")
    public String getAddUserToPage(Model model) {

        logger.info("client requested add user to book page");

        List<Book> books = bookService.getAllBooks();
        List<User> users = userService.getAllUsers();
        logger.info("books recieved successfully.books size - {}", books.size());
        logger.info("users recieved successfully.users size - {}", users.size());
        model.addAttribute("books", books);
        model.addAttribute("users", users);

        return "addusertobook";
    }


    @PostMapping("/addusers")
    public String insertUsersToBook( @ModelAttribute AdduserToBookForm form, RedirectAttributes redirectAttributes) {
       
        logger.info("client updating users to the book");
        String message = addUserService.AddUsersToBook(form);
        logger.info("client successfully updated users and redirecting to the addusers page ");
        redirectAttributes.addFlashAttribute("message", message);

        return "redirect:/addusers";
    }

    @GetMapping("addusers/users/{bookid}")
    @ResponseBody
    public ResponseEntity<List<User>> getUsersWithBookId(@PathVariable("bookid") int bookid) {

        logger.info("Client requested users with bookid");
        List<User> users = userService.getAllUsers(bookid);
        logger.info("Recieved users with bookId - {}", bookid);

        return new ResponseEntity<>(users, HttpStatus.OK);

    }



}
