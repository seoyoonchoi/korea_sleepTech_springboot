package com.example.korea_sleepTech_springboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class C_Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //nullable은 null가능 여부이고, 최대 50글자까지(50글자부터는 짤림)
    @Column(nullable = false, length = 50)
    private String writer;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 500)
    private String content;

    @Enumerated(EnumType.STRING) // JPA 에서 열거형 데이터를 데이터베이스에 저장할 때에는 문자열로 저장한다.
    @Column(nullable = false)
    private C_Category category;


}
