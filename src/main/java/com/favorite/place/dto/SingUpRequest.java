package com.favorite.place.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
public class SingUpRequest {

    @NotNull
    @Length(min = 2, max = 32, message = "이름은 2자 이상 16자 이하여야 합니다.")
    private String name;
    @NotNull
    @Pattern(regexp = "^(010|011|016|017|018|019)(\\d{3,4})(\\d{4})$", message = "유효하지 않은 휴대폰 번호 형식입니다.")
    private String phoneNumber;
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9-_.]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]{2,4}$", message = "유효하지 않은 이메일 주소 형식입니다.")
    private String email;
    @NotNull
    @Length(min = 9, max = 128, message = "비밀번호는 9자 이상 128자 이하여야 합니다.")
    private String password;
    @NotNull
    private String userRole;

}
