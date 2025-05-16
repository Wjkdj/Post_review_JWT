package com.test.service.implementations;

import com.test.common.ResponseMessage;
import com.test.dto.request.NoticeCreateRequestDto;
import com.test.dto.response.NoticeDetailResponeDto;
import com.test.dto.response.ResponseDto;
import com.test.entity.Notice;
import com.test.repository.NoticeRepository;
import com.test.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;

    @Override
    public ResponseDto<NoticeDetailResponeDto> createNotice(NoticeCreateRequestDto dto) {
        NoticeDetailResponeDto responseDto = null;

        Notice newNotice = Notice.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();

        Notice saveNotice = noticeRepository.save(newNotice);

        responseDto = NoticeDetailResponeDto.builder()
                .title(saveNotice.getTitle())
                .content(saveNotice.getContent())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }
}
