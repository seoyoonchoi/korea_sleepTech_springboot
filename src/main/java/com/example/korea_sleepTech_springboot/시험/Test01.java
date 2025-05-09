package com.example.korea_sleepTech_springboot.시험;


public class Test01 {

    /*
   01번 :3
   02번 :4
   03번 :3
   04번 :2
   05번 :3
   06번 :4
   07번 :4
   08번 :4
   09번 :3
   10번 :4
   11번 :@RequestMapping
   12번 :delete
   13번 :@pathVariable
   14번 :@bindParam
   15번 : 서비스 계층
   16번 : 저장..?
   17번 : put
   18번 : controller
   19번 :
@RestController
@RequestMapping("/hello")
@RequiredArgsConstructor
public class HelloController{
public String hello{
return "Hello, SpringBoot"
}
}

   20번 :
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
}

   */


    //느낀점
    //난이도 : 보통
    //스스로 구현정도 : 현재까지 쓰인 모든 메서드 (HTTPStatus)등을 외우고 있지는 않아서 완벽하게 혼자 구현하려면 좀더 암기의 필요성이 느껴집니다
    //이해정도 : 동작 원리는 다 이해갑니다
    //추가사항 : 대충 어떻게 동작하는지는 이해가 가지만 그걸 이론으로 명확하게 말하기는 어려운듯 합니다
    //+) API가 뭔지 기억이 안나서 API가 뭔지 인터넷 검색하고 나서 19번 작성하였습니다
    //+) 중간에 제네릭 타입을 임의로 DATA 로 설정했다가 에러가 나서 해당 부분 검색 후 재작성하였습니다



}
