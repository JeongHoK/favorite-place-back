package com.favorite.place.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDTO {

    private String name;
    private String phoneNumber;
    private String email;
    private String password;
    private String userRole;

}
