package com.example.hr.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.hr.dto.EmployeeDto;
import com.example.hr.entity.Department;
import com.example.hr.entity.Employee;
import com.example.hr.exception.ResourceNotFoundException;
import com.example.hr.mapper.EmployeeMapper;
import com.example.hr.repository.DepartmentRepository;
import com.example.hr.repository.EmployeeRepository;
import com.example.hr.service.EmployeeService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	
    private static final Logger log =
            LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(
            EmployeeRepository employeeRepository,
            DepartmentRepository departmentRepository,
            EmployeeMapper employeeMapper) {

        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.employeeMapper = employeeMapper;
    }
    
    @CacheEvict(value = "employees", allEntries = true)

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
    	
        log.info("Creating employee with code: {}",
                employeeDto.getEmployeeCode());

        Department department = departmentRepository
                .findById(employeeDto.getDepartmentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department not found with id: "
                                        + employeeDto.getDepartmentId()));

        Employee employee =
                employeeMapper.toEntity(employeeDto, department);

        Employee savedEmployee =
                employeeRepository.save(employee);

        return employeeMapper.toDto(savedEmployee);
        
      }
    
    @CacheEvict(value = "employees", allEntries = true)

    @Override
    public EmployeeDto updateEmployee(
            Long id,
            EmployeeDto employeeDto) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found with id: " + id));

        Department department = departmentRepository
                .findById(employeeDto.getDepartmentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department not found with id: "
                                        + employeeDto.getDepartmentId()));

        employeeMapper.updateEntity(
                employee,
                employeeDto,
                department);

        Employee updatedEmployee =
                employeeRepository.save(employee);

        return employeeMapper.toDto(updatedEmployee);
    }
    
    @Cacheable(value = "employees", key = "#id")
    
    @Override
    @Transactional(readOnly = true)
    public EmployeeDto getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found with id: " + id));

        return employeeMapper.toDto(employee);
    }
    
    @Cacheable(value = "employees")

    @Override
    @Transactional(readOnly = true)
    public Page<EmployeeDto> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable)
                .map(employeeMapper::toDto);
    }
    
    @CacheEvict(value = "employees", allEntries = true)

    @Override
    public void deleteEmployee(Long id) {
    	
        log.info("Deleting employee with id: {}", id);

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found with id: " + id));

        employeeRepository.delete(employee);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDto> getEmployeesByDepartmentName(
            String departmentName) {

        return employeeRepository
                .findEmployeesByDepartmentName(departmentName)
                .stream()
                .map(employeeMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDto> getEmployeesWithMinimumSalary(
            double minimumSalary) {

        return employeeRepository
                .findEmployeesWithMinimumSalary(minimumSalary)
                .stream()
                .map(employeeMapper::toDto)
                .toList();
    }
}
