package com.example.hr.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.hr.dto.LeaveDto;
import com.example.hr.entity.Employee;
import com.example.hr.entity.Leave;
import com.example.hr.exception.ResourceNotFoundException;
import com.example.hr.mapper.LeaveMapper;
import com.example.hr.repository.EmployeeRepository;
import com.example.hr.repository.LeaveRepository;
import com.example.hr.service.LeaveService;

@Service
@Transactional
public class LeaveServiceImpl implements LeaveService {
	
    private static final Logger log =
            LoggerFactory.getLogger(LeaveServiceImpl.class);

    private final LeaveRepository leaveRepository;
    private final EmployeeRepository employeeRepository;
    private final LeaveMapper leaveMapper;

    public LeaveServiceImpl(
            LeaveRepository leaveRepository,
            EmployeeRepository employeeRepository,
            LeaveMapper leaveMapper) {

        this.leaveRepository = leaveRepository;
        this.employeeRepository = employeeRepository;
        this.leaveMapper = leaveMapper;
    }
    
    @CacheEvict(value = "leaves", allEntries = true)

    @Override
    public LeaveDto createLeave(LeaveDto leaveDto) {
    	
        log.info("Creating leave for employee id: {}",
                leaveDto.getEmployeeId());

        Employee employee = employeeRepository
                .findById(leaveDto.getEmployeeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found with id: "
                                        + leaveDto.getEmployeeId()));

        Leave leave = leaveMapper.toEntity(leaveDto, employee);

        Leave savedLeave = leaveRepository.save(leave);

        return leaveMapper.toDto(savedLeave);
    }
    
    @CacheEvict(value = "leaves", allEntries = true)

    @Override
    public LeaveDto updateLeave(
            Long id,
            LeaveDto leaveDto) {

        Leave leave = leaveRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Leave not found with id: " + id));

        Employee employee = employeeRepository
                .findById(leaveDto.getEmployeeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found with id: "
                                        + leaveDto.getEmployeeId()));

        leaveMapper.updateEntity(
                leave,
                leaveDto,
                employee);

        Leave updatedLeave = leaveRepository.save(leave);

        return leaveMapper.toDto(updatedLeave);
    }
    
    @Cacheable(value = "leaves", key = "#id")

    @Override
    @Transactional(readOnly = true)
    public LeaveDto getLeaveById(Long id) {

        Leave leave = leaveRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Leave not found with id: " + id));

        return leaveMapper.toDto(leave);
    }
    
    @Cacheable(value = "leaves")

    @Override
    @Transactional(readOnly = true)
    public Page<LeaveDto> getAllLeaves(Pageable pageable) {
        return leaveRepository.findAll(pageable)
                .map(leaveMapper::toDto);
    }
    
    @CacheEvict(value = "leaves", allEntries = true)

    @Override
    public void deleteLeave(Long id) {
    	
        log.info("Deleting leave with id: {}", id);

        Leave leave = leaveRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Leave not found with id: " + id));

        leaveRepository.delete(leave);
    }
}
