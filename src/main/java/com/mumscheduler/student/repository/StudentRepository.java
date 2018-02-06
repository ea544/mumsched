package com.mumscheduler.student.repository;

import com.mumscheduler.student.model.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	/**
	 * Get a course by code
	 */
	@Query("FROM Faculty f WHERE f.email=:email")
	public Student getStudentByEmail(@Param("email") String email);
	
	/**
	 * Get a course by code
	 */
	public Iterable<Student> findByFirstname(String firstname);
}
