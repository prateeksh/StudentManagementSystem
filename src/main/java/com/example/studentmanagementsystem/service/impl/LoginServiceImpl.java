package com.example.studentmanagementsystem.service.impl;

import com.example.studentmanagementsystem.model.Login;
import com.example.studentmanagementsystem.model.LoginDto;
import com.example.studentmanagementsystem.model.Role;
import com.example.studentmanagementsystem.respository.LoginRepository;
import com.example.studentmanagementsystem.respository.RoleRepository;
import com.example.studentmanagementsystem.service.LoginService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class LoginServiceImpl implements LoginService {

    private LoginRepository loginRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public LoginServiceImpl(LoginRepository loginRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.loginRepository = loginRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Login findUserByEmail(String email) {
        return loginRepository.findByEmail(email);
    }

    @Override
    public void registerUser(LoginDto login) {
        Login user = new Login();
        user.setName(login.getFirstName() + " " + login.getLastName());
        user.setEmail(login.getEmail());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(login.getPassword()));

        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        loginRepository.save(user);
    }

    private LoginDto mapToUserDto(Login user){
        LoginDto userDto = new LoginDto();
        String[] str = user.getName().split(" ");
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}
