package com.example.korea_sleepTech_springboot.repository;

import com.example.korea_sleepTech_springboot.entity.A_TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<A_TestEntity, Long> {
    //JpaRepository 의 기본메서드


}
