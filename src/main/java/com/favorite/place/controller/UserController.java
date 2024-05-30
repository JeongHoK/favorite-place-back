package com.favorite.place.controller;

import com.favorite.place.dto.LoginDTO;
import com.favorite.place.dto.UserDTO;
import com.favorite.place.entity.User;
import com.favorite.place.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;


    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
    }

    @PostMapping("/user/join")
    public void joinUser(@Valid @RequestBody UserDTO userDTO) {

        userService.joinUser(userDTO);
    }

    @GetMapping("/user/login")
    public User loginUser(@Valid @RequestBody LoginDTO loginDTO) {

        return userService.loginUser(loginDTO);
    }

}
