package com.example.korea_sleepTech_springboot.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data //Getter Setter toString 동시생성
@AllArgsConstructor(staticName = "set")
public class ResponseDto<D> {
    private boolean result;
    private String message;
    private D data;//실제 응답데이터 ( 유저정보, 게시글 목록 등)

    public static <D> ResponseDto<D> setSuccess(String message, D data) {
        return ResponseDto.set(true, message, data);
    }

    //실패응답생성
    public static <D> ResponseDto<D> setFailed(String message){
        return ResponseDto.set(false, message, null);
    }
}
