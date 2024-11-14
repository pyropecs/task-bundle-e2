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

import com.library.models.User;
import com.library.services.UserService;

@Controller
public class UserController {

   @Autowired
   private UserService userService;
   private static final Logger logger = LogManager.getLogger();

    @GetMapping("/users")
    public String createUserPage(Model model) {
        logger.info("recieved /users request to fetch createform");     
        model.addAttribute("path", "users");
        return "createform";
    }


 

    @PostMapping("/users/add")
    public String createUser( @ModelAttribute User user, RedirectAttributes redirectAttributes) {
        logger.info("Client adding user Object - {} ",user.toString());
        String message = userService.addUser(user);
        logger.info(message);
        redirectAttributes.addFlashAttribute("message", message);
        redirectAttributes.addFlashAttribute("path", "users");
        return "redirect:/users";
    }


}
