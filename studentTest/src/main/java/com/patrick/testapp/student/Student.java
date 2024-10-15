package com.patrick.testapp.student;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "students")
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Student {
		
	public Student(String name, String studentId) {
		super();
		this.name = name;
		this.studentId = studentId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	String name;
	
	String studentId;

}
