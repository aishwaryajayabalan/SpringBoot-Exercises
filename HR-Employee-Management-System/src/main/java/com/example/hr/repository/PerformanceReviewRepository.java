package com.example.hr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hr.entity.PerformanceReview;

public interface PerformanceReviewRepository
        extends JpaRepository<PerformanceReview, Long> {

}
