package com.test.service;

import com.test.dto.response.ResponseDto;
import com.test.dto.user.request.UserSignInRequestDto;
import com.test.dto.user.request.UserSignUpRequestDto;
import com.test.dto.user.response.UserSignInResponseDto;
import com.test.dto.user.response.UserSignUpResponseDto;
import jakarta.validation.Valid;

public interface AuthService{
    ResponseDto<UserSignUpResponseDto> signup(@Valid UserSignUpRequestDto dto);
    ResponseDto<UserSignInResponseDto> login(@Valid UserSignInRequestDto dto);
}
