package com.test.dto.user.response;

import com.test.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserSignInResponseDto {
    private String token;
    private String username;
    private String role;
}
