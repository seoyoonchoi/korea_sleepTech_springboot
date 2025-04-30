package com.example.korea_sleepTech_springboot.controller;


import com.example.korea_sleepTech_springboot.common.ApiMappingPattern;
import com.example.korea_sleepTech_springboot.dto.request.BookCreateRequestDto;
import com.example.korea_sleepTech_springboot.dto.request.BookUpdateRequestDto;
import com.example.korea_sleepTech_springboot.dto.response.BookResponseDto;
import com.example.korea_sleepTech_springboot.dto.response.ResponseDto;
import com.example.korea_sleepTech_springboot.entity.C_Category;
import com.example.korea_sleepTech_springboot.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.BOOK_API)
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    //create
    @PostMapping
    public ResponseEntity<ResponseDto<BookResponseDto>> createBook(@RequestBody BookCreateRequestDto dto){
        try{
            ResponseDto<BookResponseDto> book =  bookService.createBook(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(book);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    //read - all
    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getAllBooks(){
        List<BookResponseDto> books = bookService.getAllBooks();
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    //read - id
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable Long id){
        BookResponseDto book= bookService.getBookById(id);
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    //update id
    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable Long id, @RequestBody BookUpdateRequestDto dto){
        BookResponseDto book = bookService.updateBook(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }


    //delete id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    //=======검색과 필터링 기능=============

    //1) 제목에 특정 단어가 포함된 책 조회
    @GetMapping("/search/title")
    public ResponseEntity<ResponseDto<List<BookResponseDto>>> getBooksByTitleContaining(
            @RequestParam String keyword
            //경로값에 ? 이후의 데이터를 키와 값의 쌍으로 추출되는 값
    ){
        try{
        ResponseDto<List<BookResponseDto>> books = bookService.getBooksByTitleContaining(keyword);
        return ResponseEntity.status(HttpStatus.OK).body(books);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    }

    //2) 카테고리별 책 조회
    @GetMapping("/category/{category}")
    public ResponseEntity<ResponseDto<List<BookResponseDto>>> getBooksByCategory(
            @PathVariable C_Category category
    ) {
        try{
            ResponseDto<List<BookResponseDto>> books = bookService.getBooksByCategory(category);
            return ResponseEntity.status(HttpStatus.OK).body(books);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }


    }

    //3) 카테고리와 작성자별 책 조회(복합조건 검색)
    //복합조건 검색은 보통 Query Parameter을 조합해서 처리한다
    @GetMapping("/search/category-writer")
    public ResponseEntity<ResponseDto<List<BookResponseDto>>> getBooksByCategoryAndWriter(
            @RequestParam(required = false) C_Category category,
            @RequestParam String writer
    ){
        try{
            ResponseDto<List<BookResponseDto>> books = bookService.getBooksByCategoryAndWriter(category,writer);
            return ResponseEntity.status(HttpStatus.OK).body(books);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
