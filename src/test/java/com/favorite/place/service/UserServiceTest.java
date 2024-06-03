package com.favorite.place.service;

import com.favorite.place.dto.SignUpRequest;
import com.favorite.place.entity.User;
import com.favorite.place.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    void userWithdraw() {
        //given
        userService.signUp(new SignUpRequest("김정호","01012345678","testmail@naver.com","test123456","ROLE_USER"));
        User joinUser = userRepository.findByEmail("testmail@naver.com");

        //when
        userService.withdraw(joinUser.getId());

        //then
        User withdrawUser = userRepository.findByEmail("testmail@naver.com");
        assertNull(withdrawUser);



    }
}