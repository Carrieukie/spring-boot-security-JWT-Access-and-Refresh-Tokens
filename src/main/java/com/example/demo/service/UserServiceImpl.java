package com.example.demo.service;

import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.mapper.DormaintoSpringUserMapper;
import com.example.demo.repository.IRoleRepository;
import com.example.demo.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements IUserService, UserDetailsService {

    private final IUserRepository userRepo;
    private final IRoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var user = userRepo.findByUsername(username);

        if (user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        }

        log.info("User found in the database");

        return DormaintoSpringUserMapper.dormainToSpringUserMapper(user);
    }



    @Override
    public User saveUser(User user) {

        var userExists = userRepo.findByUsername(user.getUsername()) != null;
        if (userExists) {
            log.error("User found in the database");
            throw new IllegalStateException("User already exists");
        }

        log.info("Saving new user {} to the database", user.getName());

        var encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new Role {} to the database", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        log.info("Adding role {} to user {} ", roleName, userName);
        User user = userRepo.findByUsername(userName);
        Role role = roleRepo.findAllByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String userName) {
        log.info("Fetching user {}", userName);
        return userRepo.findByUsername(userName);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepo.findAll();
    }

    @Override
    public List<Role> getRoles() {
        log.info("Fetching all roles");
        return roleRepo.findAll();
    }
}
