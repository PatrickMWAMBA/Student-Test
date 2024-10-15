package com.patrick.testapp.student;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

@Service
public class StudentService {
	
	private StudentRepository studentRepository;

	public StudentService(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}
	
	public Student createStudent(Student student) {
		
		if(!studentRepository.existsByStudentId(student.getStudentId())) {
		 Student savedStudent = studentRepository.save(student);
		 System.out.println(savedStudent);
		 return savedStudent;
		}
		else 
			throw new RuntimeException("Student ID already exists");
	}
	
	public Student getStudent(Long id) {
		return studentRepository.findById(id)
				.orElseThrow(()->new RuntimeException("Student does not exist"));
	}
	
	public List<Student> getAllStudents(){
		return studentRepository.findAll();
	}
	
}
