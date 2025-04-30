package com.example.korea_sleepTech_springboot.이론;

public class J_JpaRepository {

    /*
    SpringBoot Data JPA 에서 제공하는 기본 인터페이스
    CRUD 와 페이징, 정렬을 포함한 다양한 데이ㅓ 엑세스 메서드를 제공한다.
    *
    쿼리메서드
    메서드 이름만으로 쿼리를 자동으로 생성하는 기능이며
    별도 쿼리 작성을 하지 않고 내부에서 해석되어 쿼리를 자동생성해준다
    JPQL : SQL을 기반으로 한 객체 모델용 쿼리언어

    1)쿼리 메서드 기본구조
    보통 find read get query같은 키워드를 주로 사용하며  find 를 가장 자주 사용한다
    위 키워드 뒤에 By를 붙여 조건을 명시한다

    ex)반환 타입에 findBy필드명(필드타입 필드값 명시)
    Query조건이 복잡하거나 JOIN이 필요한 경우 메서드 이름만으로 한계가 있으므로 직접 쿼리 작성 가능

    */
}
