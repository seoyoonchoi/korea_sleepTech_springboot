package com.example.korea_sleepTech_springboot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity //DBtable 과 1:1 mapping이 된다. JPA 엔티티임을 선언한다 반드시 PK 설정이 필요하다
@Table(name = "student") //클래스명과 테이블명이 다를 경우 반드시 명시하여야 한다
//@Table(
//        name = "student",
//        uniqueConstraints = {
//                @UniqueConstraint(columnNames = {"name", "email"})
//        } >> name과 email 둘다 같은 데이터가 동시에 있으면 안됨
//)
@Getter
@Setter
@NoArgsConstructor
public class B_Student {
    @Id // 해당 필드가 테이블의 PK 기본키임을 명시한다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키값을 자동생성 , AUTOINCREMENT
    private Long id;
    private String name;
    //email에서 unique 제약조건 명시하는 방법
    /*
    * 1. 컬럼 단위 명시
    * */
    @Column(unique = true)//JPA가 테이블을 만들 때 이메일 컬럼에 유니크 제약조건을 자동설정
    private String email;

    //protected B_Student(){} //JPA는 엔티티 생성시 기본 생성자를 필수로 생성해야한다.


    public B_Student(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
