package com.example.hr.mapper;

import org.springframework.stereotype.Component;

import com.example.hr.dto.LeaveDto;
import com.example.hr.entity.Employee;
import com.example.hr.entity.Leave;

@Component
public class LeaveMapper {

    public Leave toEntity(
            LeaveDto dto,
            Employee employee) {

        Leave leave = new Leave();

        leave.setLeaveType(dto.getLeaveType());
        leave.setStartDate(dto.getStartDate());
        leave.setEndDate(dto.getEndDate());
        leave.setReason(dto.getReason());
        leave.setStatus(dto.getStatus());
        leave.setEmployee(employee);

        return leave;
    }

    public LeaveDto toDto(Leave leave) {

        LeaveDto dto = new LeaveDto();

        dto.setId(leave.getId());
        dto.setLeaveType(leave.getLeaveType());
        dto.setStartDate(leave.getStartDate());
        dto.setEndDate(leave.getEndDate());
        dto.setReason(leave.getReason());
        dto.setStatus(leave.getStatus());

        if (leave.getEmployee() != null) {
            dto.setEmployeeId(leave.getEmployee().getId());
        }

        return dto;
    }

    public void updateEntity(
            Leave leave,
            LeaveDto dto,
            Employee employee) {

        leave.setLeaveType(dto.getLeaveType());
        leave.setStartDate(dto.getStartDate());
        leave.setEndDate(dto.getEndDate());
        leave.setReason(dto.getReason());
        leave.setStatus(dto.getStatus());
        leave.setEmployee(employee);
    }
}
