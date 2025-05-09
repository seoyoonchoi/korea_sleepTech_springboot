package com.example.korea_sleepTech_springboot.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
//응답 DTO는 생성 시점에 모든 데이터가 완성되어야 반환된다.기본생성자가 사실상 필요없음
/*
* 영속성 컨텍스트
* JPA에서 entity객체를 연구 저장하는 환경을 의미한다
* 엔티티 매니저에 의하여 관리 save find remove 같은 작업을 수행한다

* */
import java.util.List;
@Getter
@Builder
@AllArgsConstructor
public class PostDetailResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private List<CommentResponseDto> comments;
}
