package com.example.korea_sleepTech_springboot.이론;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class E_IoC_DI {
    /*
    제어의 역전 (IOC)


    * */

    class Book1 {
        private String title;

        public Book1(String title) {
            this.title = title;
        }

        public String getTitle() {
            return this.title;
        }
    }

    class BookStore1 {
        private Book1 book;

        public BookStore1() {
            this.book = new Book1("Spring boot 기초");
        }

        public void displayBook() {
            System.out.println("Book: " + book.getTitle());
        }
    }

    //스프링 제어의 역전프로그래밍 방식
    @Component
    class Book2 {
        private String title;

        public Book2() {
            this.title = "스프링 기초";

        }

        public String getTitle() {
            return this.title;
        }


    }

    @Component//spring container 가 해당 객체를 관리하도록 설정해주는 annotation,
            //spring bean 이라고 하며 스프링 컨테이너에 의해 관리되어 재사용 가능한 소프트웨어 컴포넌트이다

    class BookStore2 {
        private Book2 book;

        //스프링이 직접 BOOk2 객체를 생성하여 넣어주기 때문에 new Book2()을 하지 않아도 스프링 컨테이너가 객체를 스스로 만들어서 주입시켜준다
        @Autowired //스프링 컨테이너에서 해당 타입의 빈을 찾아 주입하는 어노테이션이다.
        public BookStore2(Book2 book) {
            this.book = book;
        }

        public void displayBook() {
            System.out.println("Book2 : " + book.getTitle());
        }
    }

    //1. 제어의 역전(Inversion of Control)
    /*
    프로그램의 제어 흐름을 개발자가 아닌 외부 컨테이너에 위임하는 방식
    제어의 권한이 컨테이너에게 있어서 객체의 생명 주기를 컨테이너가 관리한다

    * */
    //2. 의존성 주입(Dependency Injection)
    /*
    클래스가 필요로 하는 객체를 외부에서 주입해주는 방식
    제어의 역전을 구현하기 위한 방법
    객체 간의 의존성을 낮추고 유연성과 재사용성을 높인다

    >>1 생성자 주입방식 ******


    >>2 필드 주입방식

    >>3 세터 주입방식


    1. 스프링 컨테이너
    애플리케이션 내에서 객체의 생명 주기와 설정을 관리한다
    제어의 역전 (IoC)을 실현하고, 의존성 주입을 지원한다

    2. 빈
    스프링 컨테이너가 관리하는 객체를 의미하며
    개발자에 의해 명시적으로 정의되거나 스프링에 의해 자동으로 감지되어 관리된다
    서비스, 리포지토리, 컨트롤러 등의 역할을 수행하는 컴포넌트로 사용된다

    cf) 빈의 경우 클래스명의 첫글자를 소문자로 바꾸어 관리한다
    (Book, BookStore 클래스 >> book, bookStore 빈)



    * */
}