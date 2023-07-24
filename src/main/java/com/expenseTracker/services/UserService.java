package com.expenseTracker.services;

import com.expenseTracker.DTO.UserDto;
import com.expenseTracker.applicationOutput.SignInOutput;
import com.expenseTracker.applicationOutput.SignUpOutput;
import com.expenseTracker.entities.User;
import com.expenseTracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public SignUpOutput createUser(UserDto userDto){
        boolean status = false;
        String userEmail = userDto.getEmail();
        User user = userRepository.findByEmail(userEmail).orElse(null);
        if(user == null){
            user = new User();
            user.setEmail(userDto.getEmail());
            user.setName(userDto.getName());
            user.setPassword(userDto.getPassword());
            user.setExpenseList(new ArrayList<>());
            try {
                userRepository.save(user);
                return new SignUpOutput(true, "User saved successfully");
            }
            catch (Exception e){
                return new SignUpOutput(false, "Some ERROR has occured  " + e.getMessage());
            }
        }
        else{
            return new SignUpOutput(false, "Email is already in use.");
        }
    }

    public SignInOutput signIn(String email, String password) {
        return null;
    }
}
