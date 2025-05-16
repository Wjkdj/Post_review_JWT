package com.test.dto.user.response;

import com.test.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetUserResponseDto {
    private Long id;
    private String userName;
    private String role;

    public GetUserResponseDto(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.role = user.getRole();
    }
}
