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
        User user1=userRepo.findByemail(user.getEmail());
        System.out.print("user"+user)
        if (user1==null){
            return "not found";
        }
       else {
            if (user1.getPwrd().equals(user.getPwrd())){
                return "Login succesfull "+" "+user1.getFullName();
            }
            else {
                return "Incorrect Password";
            }
        }
    }
}
