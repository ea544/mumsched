package com.mumscheduler.faculty.repository;

import com.mumscheduler.faculty.model.Faculty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
	/**
	 * Get a course by code
	 */
	@Query("FROM Faculty f WHERE f.email=:email")
	public Faculty getFacultyByEmail(@Param("email") String email);
	
	/**
	 * Get a course by code
	 */
	public Iterable<Faculty> findByFirstname(String firstname);
}
