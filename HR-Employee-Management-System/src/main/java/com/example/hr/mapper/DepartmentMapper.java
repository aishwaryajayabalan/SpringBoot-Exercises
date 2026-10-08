package com.example.hr.mapper;

import org.springframework.stereotype.Component;

import com.example.hr.dto.DepartmentDto;
import com.example.hr.entity.Department;

@Component
public class DepartmentMapper {

    public Department toEntity(DepartmentDto dto) {

        Department department = new Department();

        department.setName(dto.getName());
        department.setDescription(dto.getDescription());

        return department;
    }

    public DepartmentDto toDto(Department department) {

        DepartmentDto dto = new DepartmentDto();

        dto.setId(department.getId());
        dto.setName(department.getName());
        dto.setDescription(department.getDescription());

        return dto;
    }

    public void updateEntity(
            Department department,
            DepartmentDto dto) {

        department.setName(dto.getName());
        department.setDescription(dto.getDescription());
    }
}
