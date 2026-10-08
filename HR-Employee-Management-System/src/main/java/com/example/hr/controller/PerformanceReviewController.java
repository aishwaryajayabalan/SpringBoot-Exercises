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

import com.example.hr.dto.PerformanceReviewDto;
import com.example.hr.service.PerformanceReviewService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/performance-reviews")
public class PerformanceReviewController {

    private final PerformanceReviewService performanceReviewService;

    public PerformanceReviewController(
            PerformanceReviewService performanceReviewService) {
        this.performanceReviewService = performanceReviewService;
    }

    @PostMapping
    public ResponseEntity<PerformanceReviewDto> createPerformanceReview(
            @Valid @RequestBody PerformanceReviewDto performanceReviewDto) {

        return new ResponseEntity<>(
                performanceReviewService
                        .createPerformanceReview(performanceReviewDto),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PerformanceReviewDto> updatePerformanceReview(
            @PathVariable Long id,
            @Valid @RequestBody PerformanceReviewDto performanceReviewDto) {

        return ResponseEntity.ok(
                performanceReviewService
                        .updatePerformanceReview(id, performanceReviewDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerformanceReviewDto> getPerformanceReviewById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                performanceReviewService
                        .getPerformanceReviewById(id));
    }

    @GetMapping
    public ResponseEntity<Page<PerformanceReviewDto>> getAllPerformanceReviews(
            Pageable pageable) {
        return ResponseEntity.ok(
                performanceReviewService.getAllPerformanceReviews(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerformanceReview(
            @PathVariable Long id) {

        performanceReviewService.deletePerformanceReview(id);

        return ResponseEntity.noContent().build();
    }
}
