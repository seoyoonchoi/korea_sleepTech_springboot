package com.example.korea_sleepTech_springboot.service;

import com.example.korea_sleepTech_springboot.entity.A_TestEntity;
import com.example.korea_sleepTech_springboot.repository.TestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestService {

    private final TestRepository testRepository;

    public TestService(TestRepository testRepository){
        this.testRepository = testRepository;
    }

    public List<A_TestEntity> getAllTests(){
        return testRepository.findAll();
    }

    public A_TestEntity createTest(A_TestEntity ATestEntity){
        return testRepository.save(ATestEntity);
    }

    public A_TestEntity getTestById(Long id) {
        Optional<A_TestEntity> optionalTestEntity = testRepository.findById(id);
        A_TestEntity test = optionalTestEntity.orElseThrow(() ->
                new RuntimeException("No id" +id));
        return test;
    }
}
