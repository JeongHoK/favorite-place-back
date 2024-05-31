package com.favorite.place.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class UserResponse {

    private Long userId;
    private String name;
    private String userRole;
    private String token;

    public UserResponse(Long userId, String name, String userRole, String token) {

        this.userId = userId;
        this.name = name;
        this.userRole = userRole;
        this.token = token;

    }


}
