package com.example.korea_sleepTech_springboot.controller;


import com.example.korea_sleepTech_springboot.dto.response.StudentResponseDto;
import com.example.korea_sleepTech_springboot.entity.B_Student;
import com.example.korea_sleepTech_springboot.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student") //해당 컨트롤러의 모든 요청URL이 "/student"로 시작함을 정의한다
public class StudentController {
    private final StudentService studentService;
    /*
    의존성 주입 방식 3가지
    필드주입. ***생성자 주입***, setter 주입

    * */
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    //학생 목록 조회(get)
    @GetMapping
    public List<StudentResponseDto> getAllStudents() {
        return studentService.getAllStudents();
    }
    //특정 아이디로 학생 조회(get)
    @GetMapping("/{id}")
    public StudentResponseDto getStudentById(@PathVariable Long id) {
        //PathVariable은 url경로에 전달된 동적 데이터를 메서드 파라미터로 매핑시켜줌
        return studentService.getStudentById(id);
    }
    //새로운 학생 등록(post)
    /*
    @Params : B_Student
    @Return : StudentDto
     */

    @PostMapping
    public StudentResponseDto createStudent(@RequestBody B_Student student) {
        //@RequestBody : 클라이언트에서 전달된 JSON데이터를  B_Student 형태로 변환해주는 녀석임
        return studentService.createStudent(student);
    }
    //학생 정보 수정 (put)

    @PutMapping("/{id}")
    public StudentResponseDto updateStudent(@PathVariable Long id, @RequestBody StudentResponseDto studentResponseDto) {
        return studentService.updateStudent(id, studentResponseDto);
    }
    //학생 정보 삭제(delete)

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();

    }
}




















