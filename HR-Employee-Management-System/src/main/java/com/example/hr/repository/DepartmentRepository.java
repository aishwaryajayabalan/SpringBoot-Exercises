package com.example.hr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hr.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}