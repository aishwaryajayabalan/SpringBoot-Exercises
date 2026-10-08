package com.example.hr.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.hr.dto.DepartmentDto;

public interface DepartmentService {

    DepartmentDto createDepartment(DepartmentDto departmentDto);

    DepartmentDto updateDepartment(Long id, DepartmentDto departmentDto);

    DepartmentDto getDepartmentById(Long id);

    Page<DepartmentDto> getAllDepartments(Pageable pageable);

    void deleteDepartment(Long id);
}
