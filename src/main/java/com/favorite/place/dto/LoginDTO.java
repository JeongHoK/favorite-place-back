package com.favorite.place.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class LoginDTO {

    @NotNull
    private String loginId;

    @NotNull
    private String password;
}
