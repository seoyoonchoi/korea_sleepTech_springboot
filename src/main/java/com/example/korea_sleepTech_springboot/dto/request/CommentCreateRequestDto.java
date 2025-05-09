package com.example.korea_sleepTech_springboot.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentCreateRequestDto {

    @NotBlank(message = "내 필수입니다")
    private String content;
    @NotBlank(message = "작성자는 필수입니다")
    private String commenter;
}
