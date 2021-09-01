package com.example.demo.mapper;

import com.example.demo.dormain.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;

public class DormaintoSpringUserMapper {
    public static org.springframework.security.core.userdetails.User dormainToSpringUserMapper(User user) {

        var name = user.getUsername();
        var password = user.getPassword();
        var authorities = new ArrayList<SimpleGrantedAuthority>();

        user.getRoles().forEach(role ->
                authorities.add(new SimpleGrantedAuthority(role.getName()))
        );

        return new org.springframework.security.core.userdetails.User(name, password, authorities);
    }
}
