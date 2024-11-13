package com.library.controllers;

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

    @GetMapping("/users")
    public String createUserPage(Model model) {
        model.addAttribute("path", "users");
        return "createform";
    }


 

    @PostMapping("/users/add")
    public String createUser( @ModelAttribute User user, RedirectAttributes redirectAttributes) {
        
        String message = userService.addUser(user);
        redirectAttributes.addFlashAttribute("message", message);
        redirectAttributes.addFlashAttribute("path", "users");
        return "redirect:/users";
    }


}
