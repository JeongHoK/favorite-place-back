package com.favorite.place.controller;

import com.favorite.place.dto.UserDTO;
import com.favorite.place.entity.User;
import com.favorite.place.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/user/join")
    public void joinUser(@Valid @RequestBody UserDTO userDTO) {
        User user = User.builder()
                            .name(userDTO.getName())
                            .password(passwordEncoder.encode(userDTO.getPassword()))
                            .phoneNumber(userDTO.getPhoneNumber())
                            .userRole(userDTO.getUserRole())
                            .email(userDTO.getEmail())
                            .build();

        userService.joinUser(user);
    }

}
