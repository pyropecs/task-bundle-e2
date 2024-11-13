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
        if(user != null){
            message = userRepository.insertUser(user)  ? "User Created Successfully":"something went wrong.please try again later";
        }else {
            message = "User not found";
        }
        
        return message;
    }
}
