package com.example.hr.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.hr.dto.LeaveDto;

public interface LeaveService {

    LeaveDto createLeave(LeaveDto leaveDto);

    LeaveDto updateLeave(Long id, LeaveDto leaveDto);

    LeaveDto getLeaveById(Long id);

    Page<LeaveDto> getAllLeaves(Pageable pageable);

    void deleteLeave(Long id);
}