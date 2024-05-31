package com.favorite.place.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class LoginRequest {

    @NotNull
    private String loginId;

    @NotNull
    private String password;

}
