package com.test.service.implementations;

import com.test.common.ResponseMessage;
import com.test.dto.response.ResponseDto;
import com.test.dto.user.request.UserSignInRequestDto;
import com.test.dto.user.request.UserSignUpRequestDto;
import com.test.dto.user.response.UserSignInResponseDto;
import com.test.dto.user.response.UserSignUpResponseDto;
import com.test.entity.User;
import com.test.provider.JwtProvider;
import com.test.repository.UserRepository;
import com.test.service.AuthService;
import com.test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;


    @Override
    public ResponseDto<UserSignUpResponseDto> signup(UserSignUpRequestDto dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();
        String confirmPassword = dto.getConfirmPassword();

        if (!password.equals(confirmPassword)) {
            return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_PASSWORD);
        }

        if (userRepository.existsByUserName(username)) {
            return ResponseDto.setFailed(ResponseMessage.EXIST_USERNAME);
        }

        String encodePassword = bCryptPasswordEncoder.encode(password);

        User user = User.builder()
                .userName(username)
                .password(encodePassword)
                .role("USER") // 기본 역할
                .build();

        UserSignUpResponseDto responseDto = new UserSignUpResponseDto(
                user.getUserName(),
                user.getRole()
        );

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }

    @Override
    public ResponseDto<UserSignInResponseDto> login(UserSignInRequestDto dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();

        User user = userRepository.findByUserName(username)
                .orElse(null);

        if (user == null) {
            return ResponseDto.setFailed(ResponseMessage.NOT_EXISTS_USER);
        }

        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return ResponseDto.setFailed(ResponseMessage.NOT_MATCH_PASSWORD);
        }

        String token = jwtProvider.generateJwtToken(user.getUserName(), user.getRole());

        UserSignInResponseDto responseDto = new UserSignInResponseDto(
                token,
                user.getUserName(),
                user.getRole()
        );

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }
}
