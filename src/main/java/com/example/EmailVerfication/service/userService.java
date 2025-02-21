package com.example.EmailVerfication.service;

import com.example.EmailVerfication.entity.User;
import com.example.EmailVerfication.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userService {
    @Autowired
    UserRepo userRepo;
    public String saveUser(User user){

            if (userRepo.existsByemail(user.getEmail())){
                return "Email already exists! Change Email";
            }
           else {
                User savedUser=  userRepo.save(user);
                if (savedUser!=null){
                    return "Registration succesfull";
                }
                else {
                    return "Registration failed";
                }
            }
    }
    public Boolean cheakEmail(String Email){
        if (userRepo.existsByemail(Email)){
            return true;

        }
        else{
            return false;
        }
    }
    public List<User> getAlluser() {
        return userRepo.findAll();
    }
    public String  LoginUser(User user){
       // Debug: Check incoming data

    try {
        System.out.println("email"+user.getEmail());
        User user1 = userRepo.findByemail(user.getEmail()); // Fetch user from DB
        System.out.println("Database Response: " + user1); // Debug: Check if DB returns user

        if (user1 == null) {
            System.out.println("User not found!");
              System.out.println("Returning not found response"); 
            return "not found";
        }

        if (user1.getPwrd().equals(user.getPwrd())) {
            System.out.println("Login successful! User: " + user1.getFullName());
            return "Login succesfull " + user1.getFullName();
        } else {
            System.out.println("Incorrect Password for user: " + user1.getEmail());
            return "Incorrect Password";
        }
    } catch (Exception e) {
        e.printStackTrace(); // Print full error
        return "Server Error";
    }
}      
}
