package com.example.hr.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.hr.dto.AttendanceDto;
import com.example.hr.entity.Attendance;
import com.example.hr.entity.Employee;
import com.example.hr.exception.ResourceNotFoundException;
import com.example.hr.mapper.AttendanceMapper;
import com.example.hr.repository.AttendanceRepository;
import com.example.hr.repository.EmployeeRepository;
import com.example.hr.service.AttendanceService;

@Service
@Transactional
public class AttendanceServiceImpl implements AttendanceService {
	
    private static final Logger log =
            LoggerFactory.getLogger(AttendanceServiceImpl.class);

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;
    private final AttendanceMapper attendanceMapper;

    public AttendanceServiceImpl(
            AttendanceRepository attendanceRepository,
            EmployeeRepository employeeRepository,
            AttendanceMapper attendanceMapper) {

        this.attendanceRepository = attendanceRepository;
        this.employeeRepository = employeeRepository;
        this.attendanceMapper = attendanceMapper;
    }
    
    @CacheEvict(value = "attendance", allEntries = true)

    @Override
    public AttendanceDto createAttendance(AttendanceDto attendanceDto) {
    	
        log.info("Creating attendance for employee id: {}",
                attendanceDto.getEmployeeId());

        Employee employee = employeeRepository
                .findById(attendanceDto.getEmployeeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found with id: "
                                        + attendanceDto.getEmployeeId()));

        Attendance attendance =
                attendanceMapper.toEntity(attendanceDto, employee);

        Attendance savedAttendance =
                attendanceRepository.save(attendance);

        return attendanceMapper.toDto(savedAttendance);
    }
    
    @CacheEvict(value = "attendance", allEntries = true)

    @Override
    public AttendanceDto updateAttendance(
            Long id,
            AttendanceDto attendanceDto) {

        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Attendance not found with id: " + id));

        Employee employee = employeeRepository
                .findById(attendanceDto.getEmployeeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found with id: "
                                        + attendanceDto.getEmployeeId()));

        attendanceMapper.updateEntity(
                attendance,
                attendanceDto,
                employee);

        Attendance updatedAttendance =
                attendanceRepository.save(attendance);

        return attendanceMapper.toDto(updatedAttendance);
    }
    
    @Cacheable(value = "attendance", key = "#id")

    @Override
    @Transactional(readOnly = true)
    public AttendanceDto getAttendanceById(Long id) {

        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Attendance not found with id: " + id));

        return attendanceMapper.toDto(attendance);
    }
    
    @Cacheable(value = "attendance")

    @Override
    @Transactional(readOnly = true)
    public Page<AttendanceDto> getAllAttendances(Pageable pageable) {
        return attendanceRepository.findAll(pageable)
                .map(attendanceMapper::toDto);
    }
    
    @CacheEvict(value = "attendance", allEntries = true)

    @Override
    public void deleteAttendance(Long id) {
    	
        log.info("Deleting attendance with id: {}", id);

        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Attendance not found with id: " + id));

        attendanceRepository.delete(attendance);
    }
}
