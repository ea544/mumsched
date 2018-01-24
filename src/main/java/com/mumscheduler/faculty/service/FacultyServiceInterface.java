package com.mumscheduler.faculty.service;

import com.mumscheduler.faculty.model.Faculty;

import java.util.List;

public interface FacultyServiceInterface {
	public Faculty save(Faculty faculty);
	public Faculty getFaculty(String email);
	public Faculty getFaculty(Long id);
	public Iterable<Faculty> findFacultyByFirstname(String firstname);
	public List<Faculty> getFacultyList();
}
