
package com.example.student.controller;

import jakarta.validation.Valid;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.student.model.Student;
import com.example.student.service.StudentService;

@RestController
public class StudentController {
		
		@Autowired
		private StudentService studentService;
	
	
	@PostMapping("/students")
	public Student registerStudent(@Valid @RequestBody Student student) {
	    return studentService.registerStudent(student);
	}
	
	@GetMapping("/students")
	public List<Student> getAllStudents() {
	    return studentService.getAllStudents();
	}
	
	@GetMapping("/students/{studentId}")
	public Student getStudentById(@PathVariable int studentId) {
	    return studentService.getStudentById(studentId);
	}
	@PutMapping("/students/{studentId}")
	public Student updateStudent(@PathVariable int studentId,
	                             @Valid @RequestBody Student student) {

	    return studentService.updateStudent(studentId, student);
	}
	
	@DeleteMapping("/students/{studentId}")
	public String deleteStudent(@PathVariable int studentId) {

	    boolean deleted = studentService.deleteStudent(studentId);

	    if (deleted) {
	        return "Student deleted successfully";
	    }
	    

	    return "Student not found";
	}
	
	
}
