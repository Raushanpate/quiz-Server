package com.example.EmailVerfication.controller;

import com.example.EmailVerfication.entity.User;
import com.example.EmailVerfication.service.OtpService;
import com.example.EmailVerfication.service.OtpServiceForReset;
import com.example.EmailVerfication.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/apiEmail")
public class Registrationcontroller {

    @Autowired
    private OtpService otpService;
    @Autowired
    private userService userService;
    @Autowired
    private OtpServiceForReset otpServiceForReset;
    // Endpoint to send OTP
    @PostMapping("/sendOtp")
    public String sendOtp(@RequestParam("email") String email ) {
        boolean isEmail=userService.cheakEmail(email);
        if (isEmail){
            return "Email Allready Exist";
        }
        else {
            boolean isOtpSent = otpService.sendOtp(email);
            if (isOtpSent) {
                return "OTP sent successfully! Please check your email.";
            } else {
                return "Failed to send OTP. Please try again.";
            }
        }
    }
    // Endpoint to verify OTP
    @PostMapping("/verifyOtp")
    public String verifyOtp(@RequestParam("email") String email, @RequestParam("otp") String otp) {
        return otpService.verifyOtp(email, otp); // Directly return the response from service
    }
    // User Controller
    @PostMapping("/saveUser")
    public String saveUser(@RequestBody User user){
        return  userService.saveUser(user);
    }
    //otp verified for reset password
    @PostMapping("/ResetOtp")
    public boolean sendOtpfoReset(@RequestParam("email") String email){
        return otpService.sendOtp(email);
    }
    //reset_Password
    //LoginUser

    @PostMapping("/LoginUser")
   public  String LoginUser(@RequestBody User user){
        

        System.out.println(user.getEmail());
        return userService.LoginUser(user);

    }
}
