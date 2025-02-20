package com.example.EmailVerfication.repo;

import com.example.EmailVerfication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {

    boolean existsByemail(String Email);

    User findByemail(String Email);
}
