package com.example.hr.mapper;

import org.springframework.stereotype.Component;

import com.example.hr.dto.PerformanceReviewDto;
import com.example.hr.entity.Employee;
import com.example.hr.entity.PerformanceReview;

@Component
public class PerformanceReviewMapper {

    public PerformanceReview toEntity(
            PerformanceReviewDto dto,
            Employee employee) {

        PerformanceReview review = new PerformanceReview();

        review.setReviewPeriod(dto.getReviewPeriod());
        review.setRating(dto.getRating());
        review.setComments(dto.getComments());
        review.setReviewDate(dto.getReviewDate());
        review.setEmployee(employee);

        return review;
    }

    public PerformanceReviewDto toDto(PerformanceReview review) {

        PerformanceReviewDto dto = new PerformanceReviewDto();

        dto.setId(review.getId());
        dto.setReviewPeriod(review.getReviewPeriod());
        dto.setRating(review.getRating());
        dto.setComments(review.getComments());
        dto.setReviewDate(review.getReviewDate());

        if (review.getEmployee() != null) {
            dto.setEmployeeId(review.getEmployee().getId());
        }

        return dto;
    }

    public void updateEntity(
            PerformanceReview review,
            PerformanceReviewDto dto,
            Employee employee) {

        review.setReviewPeriod(dto.getReviewPeriod());
        review.setRating(dto.getRating());
        review.setComments(dto.getComments());
        review.setReviewDate(dto.getReviewDate());
        review.setEmployee(employee);
    }
}
