package com.favorite.place.controller;

import com.favorite.place.dto.UserDTO;
import com.favorite.place.entity.User;
import com.favorite.place.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/join")
    public void joinUser(@RequestBody UserDTO userDTO) {

        User user = User.builder()
                            .name(userDTO.getName())
                            .password(userDTO.getPassword())
                            .phoneNumber(userDTO.getPhoneNumber())
                            .userRole(userDTO.getUserRole())
                            .email(userDTO.getEmail())
                            .build();

        userService.joinUser(user);
    }

}
