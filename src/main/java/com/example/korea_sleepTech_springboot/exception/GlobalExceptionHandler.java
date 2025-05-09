package com.example.korea_sleepTech_springboot.exception;

import com.example.korea_sleepTech_springboot.dto.response.ResponseDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //400 잘못된 요청
    @ExceptionHandler({IllegalArgumentException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<ResponseDto<?>> handleBadRequest(Exception e){
        e.printStackTrace();
        ResponseDto<?> response = ResponseDto.setFailed("Badrequest"+e.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
    //403 권한없음
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseDto<?>> handleAccessDenied(AccessDeniedException e){
        e.printStackTrace();
        ResponseDto<?> response = ResponseDto.setFailed("AccessDenied"+e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);

    }
    //404 엔티티 못찾음
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseDto<?>> handleNotFoundException(EntityNotFoundException e){
        e.printStackTrace();
        ResponseDto<?> response = ResponseDto.setFailed("NotFound"+e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    //409 데이터 충돌 - 무결성-DB제약조건 위반
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseDto<?>> handleDataIntegrityViolationException(DataIntegrityViolationException e){
        e.printStackTrace();
        ResponseDto<?> response = ResponseDto.setFailed("conflict"+e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
    //500 서버오류

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto<?>> handleException(Exception e){
        e.printStackTrace();
        ResponseDto<?> response = ResponseDto.setFailed("internal server Error"+e.getMessage());
        return ResponseEntity.internalServerError().body(response);
    }
}
