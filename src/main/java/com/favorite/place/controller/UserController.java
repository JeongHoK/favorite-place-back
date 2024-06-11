package com.favorite.place.controller;

import com.favorite.place.entity.Place;
import com.favorite.place.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping("/checking/withdraw/{userId}")
    public void withdraw(@PathVariable Long userId) {
        userService.withdraw(userId);
    }

    @GetMapping("/checking/myPlace/{userId}")
    public List<Place> getMyPlace(@PathVariable Long userId) {

        return userService.getMyPlace(userId);
    }


}
