package com.patrick.testapp.student;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class StudentRepositoryTest {
	
	@Autowired
	private StudentRepository studentRepositoryForTest;
	
	@AfterEach
	void clearDatabase() {
		studentRepositoryForTest.deleteAll();
	}

	@Test
	void testExistsByStudentId() {
		
		//given
			String studentId = "17111688";
			Student studentUnderTest = new Student("patrick", studentId);
			studentRepositoryForTest.save(studentUnderTest);
		
		//when
			boolean existsByStudentId = studentRepositoryForTest.existsByStudentId(studentId);
		
		//then
			assertThat(existsByStudentId).isTrue();
	}
	
	@Test
	void testDoesntExistByStudentId() {
		
		//given
			String studentId = "17111688";
			Student studentUnderTest = new Student("patrick", studentId);
		
		//when
			boolean existsByStudentId = studentRepositoryForTest.existsByStudentId(studentId);
		
		//then
			assertThat(existsByStudentId).isFalse();
			
	}


}
