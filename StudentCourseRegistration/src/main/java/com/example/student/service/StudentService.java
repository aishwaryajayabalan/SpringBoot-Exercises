package com.example.student.service;

import java.util.ArrayList;
import java.util.List;

import com.example.student.exception.DuplicateStudentException;
import com.example.student.exception.StudentNotFoundException;
import com.example.student.model.Student;

import jakarta.validation.Valid;

import org.springframework.stereotype.Service;

@Service
public class StudentService {
	
	
	private List<Student> students = new ArrayList<>();
	
	public Student registerStudent(Student student) {

	    for (Student existingStudent : students) {

	        if (existingStudent.getStudentId() == student.getStudentId()) {
	            throw new DuplicateStudentException(
	                "Student already exists with id: " + student.getStudentId()
	            );
	        }
	    }

	    students.add(student);
	    return student;
	
	}
	
	public List<Student> getAllStudents() {
	    return students;
	}
	
	public Student getStudentById(int studentId) {

	    for (Student student : students) {

	        if (student.getStudentId() == studentId) {
	            return student;
	        }
	    }

	    throw new StudentNotFoundException("Student not found with id: " + studentId);
	}
	
	public boolean deleteStudent(int studentId) {

	    for (Student student : students) {

	        if (student.getStudentId() == studentId) {
	            students.remove(student);
	            return true;
	        }
	    }

	    throw new StudentNotFoundException("Student not found with id: " + studentId);
	}

	public Student updateStudent(int studentId, Student student) {

	    for (Student existingStudent : students) {

	        if (existingStudent.getStudentId() == studentId) {

	            existingStudent.setStudentName(student.getStudentName());
	            existingStudent.setCourseName(student.getCourseName());
	            existingStudent.setEmail(student.getEmail());
	            existingStudent.setAge(student.getAge());

	            return existingStudent;
	        }
	    }

	    throw new StudentNotFoundException(
	        "Student not found with id: " + studentId
	    );
	
	}

}
