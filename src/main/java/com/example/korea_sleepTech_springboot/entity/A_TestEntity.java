package com.example.korea_sleepTech_springboot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "test")//클래스명과 테이블명이 다를 경우 name 속성으로 연결할 테이블명을 반드시 명시하여야 한다
@NoArgsConstructor
@Getter
@Setter
public class A_TestEntity {
    @Id //기본키 설정 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY)//자동 증가
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;




}
