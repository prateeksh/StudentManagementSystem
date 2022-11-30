package com.example.studentmanagementsystem.service;

import com.example.studentmanagementsystem.model.Login;
import com.example.studentmanagementsystem.model.LoginDto;
import com.example.studentmanagementsystem.model.Student;

public interface LoginService {

    Login findUserByEmail(String email);

    void registerUser(LoginDto login);
}
