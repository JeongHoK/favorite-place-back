package com.favorite.place.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {

    private Long userId;
    private String name;
    private String userRole;
    private String token;

    public LoginResponse(Long userId, String name, String userRole, String token) {

        this.userId = userId;
        this.name = name;
        this.userRole = userRole;
        this.token = token;

    }


}
