package com.example.demo.repo;

import com.example.demo.dormain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findByUsername(String Username);
}
