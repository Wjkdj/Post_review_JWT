package com.test.service;

import com.test.dto.response.ResponseDto;
import com.test.dto.user.response.GetUserResponseDto;

public interface UserService {
    ResponseDto<GetUserResponseDto> getUserInfo(String userName);
}
