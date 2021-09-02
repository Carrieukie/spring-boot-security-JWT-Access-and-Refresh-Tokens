package com.example.demo;

import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.jwt.CustomAuthenticationFilter;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.service.IUserService;
import com.example.demo.util.TokenUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(UserServiceApplication.class, args);

    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    Algorithm algorithm(){
        return Algorithm.HMAC256("secret".getBytes());
    }



    @Bean
    CommandLineRunner run(IUserService userService){
        return args -> {

            userService.saveRole(new Role(null,"ROLE_USER"));
            userService.saveRole(new Role(null,"ROLE_MANAGER"));
            userService.saveRole(new Role(null,"ROLE_ADMIN"));
            userService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));

            userService.saveUser(new User(null,"John Travolta","john","1234",new ArrayList<>()));
            userService.saveUser(new User(null,"Will Smith","will","1234",new ArrayList<>()));
            userService.saveUser(new User(null,"Jim Carry","jim","1234",new ArrayList<>()));
            userService.saveUser(new User(null,"Arnold Schwazenegger","arnold","1234",new ArrayList<>()));

            userService.addRoleToUser("john","ROLE_USER");
            userService.addRoleToUser("will","ROLE_MANAGER");
            userService.addRoleToUser("jim","ROLE_ADMIN");
            userService.addRoleToUser("arnold","ROLE_ADMIN");
            userService.addRoleToUser("arnold","ROLE_SUPER_ADMIN");
            userService.addRoleToUser("arnold","ROLE_MANAGER");
            userService.addRoleToUser("arnold","ROLE_USER");

        };
    }

}
