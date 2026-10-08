package com.example.hr.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.hr.dto.PerformanceReviewDto;
import com.example.hr.entity.Employee;
import com.example.hr.entity.PerformanceReview;
import com.example.hr.exception.ResourceNotFoundException;
import com.example.hr.mapper.PerformanceReviewMapper;
import com.example.hr.repository.EmployeeRepository;
import com.example.hr.repository.PerformanceReviewRepository;
import com.example.hr.service.PerformanceReviewService;

@Service
@Transactional
public class PerformanceReviewServiceImpl implements PerformanceReviewService {
	
    private static final Logger log =
            LoggerFactory.getLogger(PerformanceReviewServiceImpl.class);

    private final PerformanceReviewRepository performanceReviewRepository;
    private final EmployeeRepository employeeRepository;
    private final PerformanceReviewMapper performanceReviewMapper;

    public PerformanceReviewServiceImpl(
            PerformanceReviewRepository performanceReviewRepository,
            EmployeeRepository employeeRepository,
            PerformanceReviewMapper performanceReviewMapper) {

        this.performanceReviewRepository = performanceReviewRepository;
        this.employeeRepository = employeeRepository;
        this.performanceReviewMapper = performanceReviewMapper;
    }
    
    @CacheEvict(value = "performanceReviews", allEntries = true)

    @Override
    public PerformanceReviewDto createPerformanceReview(
    		
            PerformanceReviewDto performanceReviewDto) {
    	
    	 log.info("Creating performance review for employee id: {}",
                 performanceReviewDto.getEmployeeId());

        Employee employee = employeeRepository
                .findById(performanceReviewDto.getEmployeeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found with id: "
                                        + performanceReviewDto.getEmployeeId()));

        PerformanceReview review =
                performanceReviewMapper.toEntity(
                        performanceReviewDto,
                        employee);

        PerformanceReview savedReview =
                performanceReviewRepository.save(review);

        return performanceReviewMapper.toDto(savedReview);
    }
    
    @CacheEvict(value = "performanceReviews", allEntries = true)

    @Override
    public PerformanceReviewDto updatePerformanceReview(
            Long id,
            PerformanceReviewDto performanceReviewDto) {

        PerformanceReview review =
                performanceReviewRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Performance review not found with id: "
                                                + id));

        Employee employee = employeeRepository
                .findById(performanceReviewDto.getEmployeeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found with id: "
                                        + performanceReviewDto.getEmployeeId()));

        performanceReviewMapper.updateEntity(
                review,
                performanceReviewDto,
                employee);

        PerformanceReview updatedReview =
                performanceReviewRepository.save(review);

        return performanceReviewMapper.toDto(updatedReview);
    }
    
    @Cacheable(value = "performanceReviews", key = "#id")

    @Override
    @Transactional(readOnly = true)
    public PerformanceReviewDto getPerformanceReviewById(Long id) {

        PerformanceReview review =
                performanceReviewRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Performance review not found with id: "
                                                + id));

        return performanceReviewMapper.toDto(review);
    }
    
    @Cacheable(value = "performanceReviews")

    @Override
    @Transactional(readOnly = true)
    public Page<PerformanceReviewDto> getAllPerformanceReviews(Pageable pageable) {
        return performanceReviewRepository.findAll(pageable)
                .map(performanceReviewMapper::toDto);
    }
    
    @CacheEvict(value = "performanceReviews", allEntries = true)

    @Override
    public void deletePerformanceReview(Long id) {
    	
        log.info("Deleting performance review with id: {}", id);

        PerformanceReview review =
                performanceReviewRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Performance review not found with id: "
                                                + id));

        performanceReviewRepository.delete(review);
    }
}
