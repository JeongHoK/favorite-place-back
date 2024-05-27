package com.favorite.place.service;

import com.favorite.place.entity.User;
import com.favorite.place.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void joinUser(User user) {
        userRepository.save(user);
    }



}
