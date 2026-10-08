package com.example.hr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hr.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

}