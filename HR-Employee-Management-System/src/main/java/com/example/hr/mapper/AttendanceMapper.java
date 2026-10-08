package com.example.hr.mapper;

import org.springframework.stereotype.Component;

import com.example.hr.dto.AttendanceDto;
import com.example.hr.entity.Attendance;
import com.example.hr.entity.Employee;

@Component
public class AttendanceMapper {

    public Attendance toEntity(
            AttendanceDto dto,
            Employee employee) {

        Attendance attendance = new Attendance();

        attendance.setAttendanceDate(dto.getAttendanceDate());
        attendance.setStatus(dto.getStatus());
        attendance.setCheckInTime(dto.getCheckInTime());
        attendance.setCheckOutTime(dto.getCheckOutTime());
        attendance.setEmployee(employee);

        return attendance;
    }

    public AttendanceDto toDto(Attendance attendance) {

        AttendanceDto dto = new AttendanceDto();

        dto.setId(attendance.getId());
        dto.setAttendanceDate(attendance.getAttendanceDate());
        dto.setStatus(attendance.getStatus());
        dto.setCheckInTime(attendance.getCheckInTime());
        dto.setCheckOutTime(attendance.getCheckOutTime());

        if (attendance.getEmployee() != null) {
            dto.setEmployeeId(attendance.getEmployee().getId());
        }

        return dto;
    }

    public void updateEntity(
            Attendance attendance,
            AttendanceDto dto,
            Employee employee) {

        attendance.setAttendanceDate(dto.getAttendanceDate());
        attendance.setStatus(dto.getStatus());
        attendance.setCheckInTime(dto.getCheckInTime());
        attendance.setCheckOutTime(dto.getCheckOutTime());
        attendance.setEmployee(employee);
    }
}