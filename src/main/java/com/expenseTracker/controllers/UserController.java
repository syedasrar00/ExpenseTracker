package com.expenseTracker.controllers;

import com.expenseTracker.DTO.UserDto;
import com.expenseTracker.applicationOutput.SignInOutput;
import com.expenseTracker.applicationOutput.SignUpOutput;
import com.expenseTracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/signup")
    public SignUpOutput signUpUser(@RequestBody UserDto userDto){
        return userService.createUser(userDto);
    }
    @PostMapping("/signin")
    public SignInOutput signInUser(@RequestParam String email, @RequestParam String password){
        return userService.signIn(email, password);
    }

}
