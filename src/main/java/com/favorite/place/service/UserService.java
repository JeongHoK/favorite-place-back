package com.favorite.place.service;

import com.favorite.place.dto.LoginDTO;
import com.favorite.place.dto.UserDTO;
import com.favorite.place.entity.User;
import com.favorite.place.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void joinUser(UserDTO userDTO) {

        User user = User.builder()
                .name(userDTO.getName())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .phoneNumber(userDTO.getPhoneNumber())
                .userRole(userDTO.getUserRole())
                .email(userDTO.getEmail())
                .build();

        userRepository.save(user);
    }

    public User loginUser(LoginDTO loginDTO) {
        User user = userRepository.findByEmail(loginDTO.getLoginId());

        if(user == null || !passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("ID 혹은 비밀번호를 다시 확인해주세요.");
        } else {
            return user;
        }

    }



}
