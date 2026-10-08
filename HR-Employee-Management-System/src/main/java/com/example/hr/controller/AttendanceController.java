package com.example.hr.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hr.dto.AttendanceDto;
import com.example.hr.service.AttendanceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping
    public ResponseEntity<AttendanceDto> createAttendance(
            @Valid @RequestBody AttendanceDto attendanceDto) {

        return new ResponseEntity<>(
                attendanceService.createAttendance(attendanceDto),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttendanceDto> updateAttendance(
            @PathVariable Long id,
            @Valid @RequestBody AttendanceDto attendanceDto) {

        return ResponseEntity.ok(
                attendanceService.updateAttendance(id, attendanceDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceDto> getAttendanceById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                attendanceService.getAttendanceById(id));
    }

    @GetMapping
    public ResponseEntity<Page<AttendanceDto>> getAllAttendances(Pageable pageable) {
        return ResponseEntity.ok(attendanceService.getAllAttendances(pageable));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(
            @PathVariable Long id) {

        attendanceService.deleteAttendance(id);

        return ResponseEntity.noContent().build();
    }
}
