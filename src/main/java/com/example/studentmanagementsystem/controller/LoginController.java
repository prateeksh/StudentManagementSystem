package com.example.studentmanagementsystem.controller;

import com.example.studentmanagementsystem.model.Login;
import com.example.studentmanagementsystem.model.LoginDto;
import com.example.studentmanagementsystem.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        super();
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model){

        LoginDto loginDto  = new LoginDto();
        model.addAttribute("login", loginDto);
        return "register";

        //return "register_user";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("login") LoginDto login,
                        BindingResult bindingResult,
                        Model model) {

        Login existingUser = loginService.findUserByEmail(login.getEmail());
        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            bindingResult.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("login", login);
            return "/register";
        }

        loginService.registerUser(login);
        return "redirect:/register?success";
    }
}
