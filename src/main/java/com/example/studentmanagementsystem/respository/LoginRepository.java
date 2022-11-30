package com.example.studentmanagementsystem.respository;

import com.example.studentmanagementsystem.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoginRepository extends JpaRepository<Login, Long> {

    public Login findByEmail(String email);

}
