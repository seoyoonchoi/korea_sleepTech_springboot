package com.example.korea_sleepTech_springboot.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCreateRequestDto {
    @NotBlank(message = "제목은 필수입니다")
    private String title;
    @NotBlank(message = "내용은 필수입니다")

    private String content;
    @NotBlank(message = "작성자는 필수입니다")

    private String author;
}
