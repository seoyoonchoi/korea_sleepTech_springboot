package com.example.korea_sleepTech_springboot.dto.response;

import java.util.List;

public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    private List<CommentResponseDto> comments;
}
