package com.patrick.testapp.student;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
	
	@Mock
	private StudentRepository studentRepository;
	
	private StudentService studentServiceUnderTest;

	@BeforeEach
	void setUp() throws Exception {
		
		studentServiceUnderTest = new StudentService(studentRepository);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		
	}

	@Test
	@Disabled
	void testStudentService() {
		fail("Not yet implemented");
	}

	@Test
	void testCreateStudent() {
		
		//given
		Student studentUnderTest = new Student("patrick", "17111688");
		
		//when
		studentServiceUnderTest.createStudent(studentUnderTest);
		
		//then
		ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
		
		verify(studentRepository).save(studentArgumentCaptor.capture());
		
		Student studentCaptured = studentArgumentCaptor.getValue();
		
		assertThat(studentCaptured).isEqualTo(studentUnderTest);
	}
	
	@Test
	void throwingExceptionTest() {
		
		//given
		Student studentUnderTest = new Student("patrick", "17111688");
		
		given(studentRepository.existsByStudentId(studentUnderTest.getStudentId()))
				.willReturn(true);
		
		assertThatThrownBy(()-> studentServiceUnderTest.createStudent(studentUnderTest))
				.isInstanceOf(RuntimeException.class)
				.hasMessageContaining("Student ID already exists");
		
	}

	@Test
	void testGetStudent() {
		//givewm
		String studentId = "17111688";
		Student studentUnderTest = new Student("patrick", studentId);
		Student student = studentServiceUnderTest.createStudent(studentUnderTest);
		System.out.println(student);
		
		//when
		//Student studentReturned = studentServiceUnderTest.getStudent(student.getId());
		List<Student> allStudents = studentServiceUnderTest.getAllStudents();

		//then
		//assertThat(studentReturned.name).isEqualTo(studentUnderTest.name);
		//assertThat(studentReturned.studentId).isEqualTo(studentUnderTest.studentId);

		assertThat(allStudents.size()).isEqualTo(1);

		
		
	}

	@Test
	void testGetAllStudents() {
		//given
		
		//when
		studentServiceUnderTest.getAllStudents();
		
		//then
		verify(studentRepository).findAll();
		
		
	}

}
