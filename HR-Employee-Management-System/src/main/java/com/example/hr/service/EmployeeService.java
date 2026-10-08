package com.example.hr.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.hr.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long id);

    Page<EmployeeDto> getAllEmployees(Pageable pageable);

    void deleteEmployee(Long id);
    
    List<EmployeeDto> getEmployeesByDepartmentName(String departmentName);

    List<EmployeeDto> getEmployeesWithMinimumSalary(double minimumSalary);
}
