package com.example.braulio.userapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.braulio.userapi.dto.LoginRequest;
import com.example.braulio.userapi.dto.LoginResponse;
import com.example.braulio.userapi.model.User; 
import com.example.braulio.userapi.util.AESUtil; 
@Service
public class AuthService {

    @Autowired
    private UserService userService;

    public LoginResponse authenticate(LoginRequest request) {
        Optional<User> userOpt = userService.findByTaxId(request.getTaxId());

        if (userOpt.isEmpty()) {
            return new LoginResponse(false, "User not found");
        }

        User user = userOpt.get();
        String encryptedInput = AESUtil.encrypt(request.getPassword());

        if (user.getPassword().equals(encryptedInput)) {
            return new LoginResponse(true, "Login successful");
        } else {
            return new LoginResponse(false, "Invalid credentials");
        }
    }

}
