package com.example.hr.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.hr.dto.PerformanceReviewDto;

public interface PerformanceReviewService {

    PerformanceReviewDto createPerformanceReview(
            PerformanceReviewDto performanceReviewDto);

    PerformanceReviewDto updatePerformanceReview(
            Long id,
            PerformanceReviewDto performanceReviewDto);

    PerformanceReviewDto getPerformanceReviewById(Long id);

    Page<PerformanceReviewDto> getAllPerformanceReviews(Pageable pageable);

    void deletePerformanceReview(Long id);
}