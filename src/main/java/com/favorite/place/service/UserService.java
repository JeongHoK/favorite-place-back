package com.favorite.place.service;

import com.favorite.place.dto.LoginRequest;
import com.favorite.place.dto.SignUpRequest;
import com.favorite.place.dto.LoginResponse;
import com.favorite.place.dto.UpdateMyInformationRequest;
import com.favorite.place.entity.Place;
import com.favorite.place.entity.User;
import com.favorite.place.jwt.JwtTokenProvider;
import com.favorite.place.repository.PlaceRepository;
import com.favorite.place.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PlaceRepository placeRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, PlaceRepository placeRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.placeRepository = placeRepository;
    }

    public ResponseEntity<String> signUp(SignUpRequest signUpRequest) {
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
                .userRole("ROLE_USER")
                .email(signUpRequest.getEmail())
                .build();

        userRepository.save(user);

        return new ResponseEntity<>(HttpStatus.OK.toString(), new HttpHeaders(), HttpStatus.OK);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getLoginId());

        if(user == null || !passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("ID 혹은 비밀번호를 다시 확인해주세요.");
        } else {
            return LoginResponse.builder()
                    .userId(user.getId())
                    .name(user.getName())
                    .userRole(user.getUserRole())
                    .token(jwtTokenProvider.createJwtToken(user.getId(), user.getUserRole()))
                    .build();
        }

    }

    public void withdraw(Long userId) {
        userRepository.deleteById(userId);
    }

    public List<Place> getMyPlace(Long userId) {

        return placeRepository.findByUserIdAndIsDeletedFalse(userId);
    }

    public void updateMyInformation(Long userId, UpdateMyInformationRequest updateMyInformationRequest) {
        User user = userRepository.findByPhoneNumber(updateMyInformationRequest.getPhoneNumber());

        if(user != null) {
            throw new IllegalArgumentException("이미 존재하는 휴대폰 번호입니다.");
        }

        User findUser = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
        findUser.updateMyInformation(updateMyInformationRequest.getPhoneNumber(), passwordEncoder.encode(updateMyInformationRequest.getPassword()));

        userRepository.save(findUser);
    }



}
