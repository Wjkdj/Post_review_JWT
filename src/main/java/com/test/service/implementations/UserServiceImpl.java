package com.test.service.implementations;

import com.test.common.ResponseMessage;
import com.test.dto.response.ResponseDto;
import com.test.dto.user.response.GetUserResponseDto;
import com.test.entity.User;
import com.test.repository.UserRepository;
import com.test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public ResponseDto<GetUserResponseDto> getUserInfo(String userName) {
        User user = userRepository.findByUserName(userName).orElse(null);

        if (user == null) {
            return ResponseDto.setFailed(ResponseMessage.NOT_EXISTS_USER);
        }

        GetUserResponseDto data = new GetUserResponseDto(user);

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
