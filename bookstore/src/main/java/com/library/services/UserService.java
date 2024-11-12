package com.library.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.models.User;
import com.library.repositories.UserRepository;

@Service
public class UserService {
        @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(int... bookidIntegers){
    
        return userRepository.getAllUsers(bookidIntegers);
    }
    
    public String addUser(User user){
        String message;
        try {
            userRepository.insertUser(user);
            message = "User Created Successfully";
        } catch (Exception e) {
            System.out.println("something went wrong UserController.createUser()");
            System.out.println(e.getMessage());
            e.printStackTrace();
            message = "Internal Server Error";
        }
        return message;
    }
}
