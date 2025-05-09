package com.example.korea_sleepTech_springboot.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentUpdateRequestDto {
    @NotBlank(message = "mandatory")
    private String content;
}
