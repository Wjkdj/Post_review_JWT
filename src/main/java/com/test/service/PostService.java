package com.test.service;

import com.test.dto.request.PostCreateRequestDto;
import com.test.dto.request.PostUpdateRequestDto;
import com.test.dto.response.PostDetailResponseDto;
import com.test.dto.response.PostListResponseDto;
import com.test.dto.response.ResponseDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    ResponseDto<PostDetailResponseDto> createPost(@Valid PostCreateRequestDto dto);

    ResponseDto<PostDetailResponseDto> updatePost(Long id, @Valid PostUpdateRequestDto dto);

    ResponseDto<List<PostListResponseDto>> getAllPosts();

    ResponseDto<PostDetailResponseDto> getPostById(Long id);
}
