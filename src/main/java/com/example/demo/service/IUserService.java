package com.example.demo.service;

import com.example.demo.dormain.Role;
import com.example.demo.dormain.User;

import java.util.List;

public interface IUserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String userName, String roleName);
    User getUser(String userName);
    List<User> getUsers();
    List<Role> getRoles();
}
