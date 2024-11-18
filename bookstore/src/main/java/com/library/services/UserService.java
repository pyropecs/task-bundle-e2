package com.library.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.models.User;
import com.library.repositories.UserRepository;

@Service
public class UserService {
        @Autowired
    private UserRepository userRepository;
    private static final Logger logger = LogManager.getLogger();

    public List<User> getAllUsers(int... bookidIntegers){ 
        logger.info("getting the users with bookid - {}",bookidIntegers);
        
        return userRepository.getAllUsers(bookidIntegers);
    }
    
    public String addUser(User user){
        String message;
        if(user != null){
            message = userRepository.insertUser(user)  ? "User Created Successfully":"something went wrong.please try again later";
            logger.info("adding user to the database result - {}",message);
        }else {
            message = "User not found";
            logger.error("{}",message);
        }
        return message;
    }
}
