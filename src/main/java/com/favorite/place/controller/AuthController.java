package com.favorite.place.controller;

import com.favorite.place.dto.LoginRequest;
import com.favorite.place.dto.SignUpRequest;
import com.favorite.place.dto.UserResponse;
import com.favorite.place.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/open")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public void signUp(@Valid @RequestBody SignUpRequest singupRequest) {

        userService.signUp(singupRequest);
    }

    @PostMapping("/login")
    public UserResponse login(@Valid @RequestBody LoginRequest loginRequest) {

        return userService.login(loginRequest);
    }

}
