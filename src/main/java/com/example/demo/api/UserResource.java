package com.example.demo.api;

import com.example.demo.dormain.Role;
import com.example.demo.dormain.User;
import com.example.demo.service.IUserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserResource {

    private final IUserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUser(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getRoles(){
        return ResponseEntity.ok().body(userService.getRoles());
    }

    @PostMapping("/user/save")
    public ResponseEntity<User> saveUser(User user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?> saveRole(RoleToUser roleToUser){
        userService.addRoleToUser(roleToUser.rolename, roleToUser.username);
        return ResponseEntity.ok().build();
    }

    @Data
    static class RoleToUser{
        private String username;
        private String rolename;
    }

}
