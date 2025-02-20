package com.example.EmailVerfication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
@Service
public class OtpServiceForReset {
    private final Map<String, OtpService.OtpEntry> otpStorage = new HashMap<>();

    @Autowired
    private JavaMailSender mailSender;

    // Generate and send OTP
    public boolean sendOtp(String email) {
        try {
            // Generate 6-digit OTP
            String otp = String.valueOf(new Random().nextInt(900000) + 100000);

            // Store OTP with current timestamp
            otpStorage.put(email, new OtpService.OtpEntry(otp, System.currentTimeMillis()));

            // Send OTP via email
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Your One-Time Password (OTP)");
            message.setText("Your OTP is: " + otp + ". It is valid for 1 minute. for reset");
            mailSender.send(message);

            System.out.println("OTP Sent to: " + email + " | OTP: " + otp); // Debug log

            return true;
        } catch (Exception e) {
            System.err.println("Error sending OTP to email: " + email);
            e.printStackTrace();
            return false;
        }
    }

    // Verify OTP with 1-minute timeout
    public String verifyOtp(String email, String otp) {
        OtpService.OtpEntry entry = otpStorage.get(email); // Get OTP and timestamp

        if (entry != null) {
            long currentTime = System.currentTimeMillis(); // Current time in milliseconds
            long elapsedTime = (currentTime - entry.timestamp) / 1000; // Time elapsed in seconds

            if (elapsedTime <= 60 && entry.otp.equals(otp)) { // OTP valid for 1 minute
                otpStorage.remove(email); // Remove OTP after successful verification
                return "OTP verified successfully!";
            } else if (elapsedTime > 60) { // If more than 1 minute
                otpStorage.remove(email); // Remove expired OTP
                return "OTP expired! Please request a new one.";
            }
        }
        return "Invalid OTP or OTP expired!";
    }

    // Inner class to store OTP and timestamp
    private static class OtpEntry {
        String otp;
        long timestamp;

        public OtpEntry(String otp, long timestamp) {
            this.otp = otp;
            this.timestamp = timestamp;
        }
    }
}
