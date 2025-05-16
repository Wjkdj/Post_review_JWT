package com.test.service;

import com.test.dto.request.NoticeCreateRequestDto;
import com.test.dto.response.NoticeDetailResponeDto;
import com.test.dto.response.ResponseDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public interface NoticeService {
    ResponseDto<NoticeDetailResponeDto> createNotice(@Valid NoticeCreateRequestDto dto);
}
