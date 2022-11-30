package com.example.studentmanagementsystem.respository;

import com.example.studentmanagementsystem.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
