package com.example.korea_sleepTech_springboot.service;

import com.example.korea_sleepTech_springboot.dto.request.CommentCreateRequestDto;
import com.example.korea_sleepTech_springboot.dto.request.CommentUpdateRequestDto;
import com.example.korea_sleepTech_springboot.dto.response.CommentResponseDto;
import com.example.korea_sleepTech_springboot.시험.dto.response.ResponseDto;
import jakarta.validation.Valid;

public interface CommentService {
    ResponseDto<CommentResponseDto> createComment(Long postId, @Valid CommentCreateRequestDto dto);

    ResponseDto<CommentResponseDto> deleteComment(Long postId, Long commentId);

    ResponseDto<CommentResponseDto> updateComment(Long postId, Long commentId, CommentUpdateRequestDto dto);
}
