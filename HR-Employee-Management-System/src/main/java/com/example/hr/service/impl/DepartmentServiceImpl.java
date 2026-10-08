package com.example.hr.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.hr.dto.DepartmentDto;
import com.example.hr.entity.Department;
import com.example.hr.exception.ResourceNotFoundException;
import com.example.hr.mapper.DepartmentMapper;
import com.example.hr.repository.DepartmentRepository;
import com.example.hr.service.DepartmentService;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
	
    private static final Logger log =
            LoggerFactory.getLogger(DepartmentServiceImpl.class);

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public DepartmentServiceImpl(
            DepartmentRepository departmentRepository,
            DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }
    
    @CacheEvict(value = "departments", allEntries = true)

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
    	
        log.info("Creating department: {}", departmentDto.getName());

        Department department = departmentMapper.toEntity(departmentDto);

        Department savedDepartment =
                departmentRepository.save(department);

        return departmentMapper.toDto(savedDepartment);
    }
    
    @CacheEvict(value = "departments", allEntries = true)

    @Override
    public DepartmentDto updateDepartment(
            Long id,
            DepartmentDto departmentDto) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department not found with id: " + id));

        departmentMapper.updateEntity(department, departmentDto);

        Department updatedDepartment =
                departmentRepository.save(department);

        return departmentMapper.toDto(updatedDepartment);
    }
    
    @Cacheable(value = "departments", key = "#id")

    @Override
    @Transactional(readOnly = true)
    public DepartmentDto getDepartmentById(Long id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department not found with id: " + id));

        return departmentMapper.toDto(department);
    }
    @Cacheable(value = "departments")

    @Override
    @Transactional(readOnly = true)
    public Page<DepartmentDto> getAllDepartments(Pageable pageable) {
        return departmentRepository.findAll(pageable)
                .map(departmentMapper::toDto);
    }
    
    @CacheEvict(value = "departments", allEntries = true)

    @Override
    public void deleteDepartment(Long id) {
    	
        log.info("Deleting department with id: {}", id);

        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department not found with id: " + id));

        departmentRepository.delete(department);
    }
}