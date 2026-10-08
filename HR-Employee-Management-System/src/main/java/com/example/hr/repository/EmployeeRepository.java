package com.example.hr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.hr.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // JPQL Query
    @Query("""
            SELECT e
            FROM Employee e
            WHERE e.department.name = :departmentName
            """)
    List<Employee> findEmployeesByDepartmentName(
            @Param("departmentName") String departmentName);

    // Native SQL Query
    @Query(value = """
            SELECT *
            FROM employees
            WHERE salary >= :minimumSalary
            """, nativeQuery = true)
    List<Employee> findEmployeesWithMinimumSalary(
            @Param("minimumSalary") double minimumSalary);
}
