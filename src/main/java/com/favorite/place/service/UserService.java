package com.favorite.place.service;

import com.favorite.place.dto.LoginRequest;
import com.favorite.place.dto.SignUpRequest;
import com.favorite.place.dto.UserResponse;
import com.favorite.place.entity.User;
import com.favorite.place.jwt.JwtTokenProvider;
import com.favorite.place.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public void signUp(SignUpRequest signUpRequest) {
        User findByPhoneNumberUser =  userRepository.findByPhoneNumber(signUpRequest.getPhoneNumber());
        if(findByPhoneNumberUser != null) {
            throw new IllegalArgumentException("이미 등록된 휴대폰 번호입니다.");
        }

        User findByEmailUser = userRepository.findByEmail(signUpRequest.getEmail());
        if(findByEmailUser != null) {
            throw new IllegalArgumentException("이미 등록된 이메일니다.");
        }

        User user = User.builder()
                .name(signUpRequest.getName())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .phoneNumber(signUpRequest.getPhoneNumber())
                .userRole(signUpRequest.getUserRole())
                .email(signUpRequest.getEmail())
                .build();

        userRepository.save(user);
    }

    public UserResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getLoginId());

        if(user == null || !passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("ID 혹은 비밀번호를 다시 확인해주세요.");
        } else {
            return UserResponse.builder()
                    .userId(user.getId())
                    .name(user.getName())
                    .userRole(user.getUserRole())
                    .token(jwtTokenProvider.createJwtToken(user.getId(), user.getUserRole()))
                    .build();
        }

    }



}
