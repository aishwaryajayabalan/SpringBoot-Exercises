package com.example.hr.controller;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.hr.dto.EmployeeDto;
import com.example.hr.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(
            @Valid @RequestBody EmployeeDto employeeDto) {

        return new ResponseEntity<>(
                employeeService.createEmployee(employeeDto),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeDto employeeDto) {

        return ResponseEntity.ok(
                employeeService.updateEmployee(id, employeeDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                employeeService.getEmployeeById(id));
    }

    @GetMapping
    public ResponseEntity<Page<EmployeeDto>> getAllEmployees(Pageable pageable) {
        return ResponseEntity.ok(employeeService.getAllEmployees(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(
            @PathVariable Long id) {

        employeeService.deleteEmployee(id);

        return ResponseEntity.noContent().build();
    }
    @GetMapping("/department")
    public ResponseEntity<List<EmployeeDto>> getEmployeesByDepartmentName(
            @RequestParam String departmentName) {

        return ResponseEntity.ok(
                employeeService.getEmployeesByDepartmentName(departmentName));
    }

    @GetMapping("/minimum-salary")
    public ResponseEntity<List<EmployeeDto>> getEmployeesWithMinimumSalary(
            @RequestParam double minimumSalary) {

        return ResponseEntity.ok(
                employeeService.getEmployeesWithMinimumSalary(minimumSalary));
    }
}
