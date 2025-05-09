package com.example.korea_sleepTech_springboot.common;


public class ApiMappingPattern {
    public static final String BOOK_API = "/api/v1/books";
    public static final String POST_API = "/api/v1/posts";
    //REST API 설계
    //댓글 생성 api/v1/posts/{postId}/comments/{commentId}
    public static final String COMMENT_API = "/api/v1/posts/{postId}/comments";

}



