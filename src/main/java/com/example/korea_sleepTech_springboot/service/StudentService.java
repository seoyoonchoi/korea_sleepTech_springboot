package com.example.korea_sleepTech_springboot.service;

import com.example.korea_sleepTech_springboot.dto.response.StudentResponseDto;
import com.example.korea_sleepTech_springboot.entity.B_Student;
import com.example.korea_sleepTech_springboot.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentResponseDto> getAllStudents() {
        List<StudentResponseDto> studentResponseDtos = null;
        try {
            List<B_Student> students = studentRepository.findAll();
            studentResponseDtos = students.stream().map(student -> new StudentResponseDto(
                    student.getId(),
                    student.getName()
            )).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error occurred while fetching Student",
                    e
            );
        }
        return studentResponseDtos;
    }

    public StudentResponseDto getStudentById(Long id) {
        StudentResponseDto studentResponseDto = null;
        try {
            B_Student student = studentRepository.findById(id)
                    .orElseThrow(() -> new Error("Student not found with id: " + id));

            studentResponseDto = new StudentResponseDto(
                    student.getId(),
                    student.getName());

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error occurred while fetching Student",
                    e
            );
        }
        return studentResponseDto;
    }

    public StudentResponseDto createStudent(B_Student studentDto) {
        StudentResponseDto studentResponseDto = null;
        try {
            B_Student student = new B_Student(
                    studentDto.getName(),
                    studentDto.getEmail()

            );
            B_Student savedStudent = studentRepository.save(student);
            studentResponseDto = new StudentResponseDto(
                    savedStudent.getId(),
                    savedStudent.getName());

            return studentResponseDto;


        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error occurred while fetching Student",
                    e
            );
        }
    }

    public StudentResponseDto updateStudent(Long id, StudentResponseDto studentUpdateRequestDto) {
        try {
            B_Student student = studentRepository.findById(id)
                    .orElseThrow(() -> new Error("Student not found by Id " + id));

            student.setName(studentUpdateRequestDto.getName());
            B_Student updatedStudent = studentRepository.save(student);

            // 수정된 Student를 기반으로 ResponseDto 생성
            StudentResponseDto responseDto = new StudentResponseDto(
                    updatedStudent.getId(),
                    updatedStudent.getName()
            );

            return responseDto;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error occurred while updating Student",
                    e
            );
        }
    }


    public void deleteStudent(Long id) {
        try {
            B_Student student = studentRepository.findById(id)
                    .orElseThrow(() -> new Error("Student not found with id : " + id));
            studentRepository.delete(student);

        } catch (Exception e) {


            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error occurred while fetching Student",
                    e
            );
        }
    }
}
