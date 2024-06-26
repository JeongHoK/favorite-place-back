package com.favorite.place.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.favorite.place.dto.LoginRequest;
import com.favorite.place.dto.SignUpRequest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("회원가입")
    void singUp() throws Exception {

        String json = objectMapper.writeValueAsString(new SignUpRequest("김정호","01012345678","email@email.com","Testpassword11@@","ROLE_USER"));

        mockMvc.perform(post("/open/singup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("로그인")
    void login() throws Exception {

        String json = objectMapper.writeValueAsString(new LoginRequest("email@email.com", "Testpassword11@@"));

        mockMvc.perform(post("/open/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", 1).exists())
                .andExpect(jsonPath("$.name", "김정호").exists())
                .andExpect(jsonPath("$.userRole", "ROLE_USER").exists())
                .andExpect(jsonPath("$.token").exists());
    }
}