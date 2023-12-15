package org.iseedeadpeopleq.controller;

import jakarta.validation.Valid;
import org.iseedeadpeopleq.beans.dto.SignInDTO;
import org.iseedeadpeopleq.beans.dto.SignUpDTO;
import org.iseedeadpeopleq.service.UserService;
import org.iseedeadpeopleq.service.exception.ServiceException;
import org.iseedeadpeopleq.utills.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reg")
public class SecurityController {

    private final UserService userService;
    private final UserValidator userValidator;

    public SecurityController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping("/signInPage")
    public String signInPage(@ModelAttribute("user") SignInDTO user){
        return "signIn";
    }

    @GetMapping("/signUpPage")
    public String signUpPage(@ModelAttribute("user") SignUpDTO user){
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUp(@ModelAttribute("user") @Valid SignUpDTO sign, BindingResult bindingResult){
        userValidator.validate(sign, bindingResult);
        if(bindingResult.hasErrors()){
            return "signUp";
        }
        try {
            userService.newUser(sign.getEmail(),sign.getPassword(),sign.getUsername());
            return "redirect:/reg/signInPage";
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
