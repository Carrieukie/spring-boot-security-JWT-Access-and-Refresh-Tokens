package com.example.demo.repo;

import com.example.demo.dormain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    Role findAllByName(String name);
}
