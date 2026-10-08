package com.example.hr.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.hr.dto.AttendanceDto;

public interface AttendanceService {

    AttendanceDto createAttendance(AttendanceDto attendanceDto);

    AttendanceDto updateAttendance(Long id, AttendanceDto attendanceDto);

    AttendanceDto getAttendanceById(Long id);

    Page<AttendanceDto> getAllAttendances(Pageable pageable);

    void deleteAttendance(Long id);
}
