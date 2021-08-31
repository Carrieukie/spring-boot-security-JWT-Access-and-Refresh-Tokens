package com.example.demo;

import com.example.demo.dormain.Role;
import com.example.demo.dormain.User;
import com.example.demo.service.IUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(UserServiceApplication.class, args);

        var token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9hcGkvbG9naW4iLCJleHAiOjE2MzA0MTEyNjR9.2Uz_t2OyLgG5X3hTbK2UMvL0kmOx0cWPCmtDiysNoNw";
//        System.out.println(token.substring("Bearer ".length()));

    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
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
