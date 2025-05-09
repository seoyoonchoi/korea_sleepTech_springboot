package com.example.korea_sleepTech_springboot.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


//DTO는 생성 시점에 값이 채워지고
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostUpdateRequestDto {
    @NotBlank(message = "제목은 필수입니다")
    private String title;
    @NotBlank(message = "내용은 필수입니다")
    private String content;

}
