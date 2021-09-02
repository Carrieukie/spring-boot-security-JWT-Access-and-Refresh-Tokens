package com.example.demo.mapper;

import com.example.demo.models.Role;
import com.example.demo.models.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;

public class DormaintoSpringUserMapper {
    public static org.springframework.security.core.userdetails.User dormainToSpringUserMapper(User user) {

        var name = user.getUsername();
        var password = user.getPassword();
        var authorities = new ArrayList<SimpleGrantedAuthority>();
        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(name, password, authorities);
    }
}
