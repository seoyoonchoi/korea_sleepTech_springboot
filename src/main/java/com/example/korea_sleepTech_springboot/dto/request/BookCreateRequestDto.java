package com.example.korea_sleepTech_springboot.dto.request;

import com.example.korea_sleepTech_springboot.entity.C_Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookCreateRequestDto {
    private String writer;
    private String title;
    private String content;
    private C_Category category;
}
