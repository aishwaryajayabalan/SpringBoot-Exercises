package com.example.hr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hr.entity.Leave;

public interface LeaveRepository extends JpaRepository<Leave, Long> {

}
