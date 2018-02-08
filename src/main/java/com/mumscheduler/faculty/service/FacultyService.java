package com.mumscheduler.faculty.service;

import com.mumscheduler.faculty.model.Faculty;
import com.mumscheduler.faculty.repository.FacultyRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacultyService implements FacultyServiceInterface {

	@Autowired
	private FacultyRepository facultyRepository;
	/**
	 * Save the faculty
	 */
	@Override
	public Faculty save(Faculty faculty) {
		return facultyRepository.save(faculty);
	}

	@Override
	public Faculty getFaculty(String email) {
		return facultyRepository.getFacultyByEmail(email);
	}

	@Override
	public Faculty getFaculty(Long id) {
		return facultyRepository.getOne(id);
	}
	
	@Override
	public Iterable<Faculty> findFacultyByFirstname(String firstname) {
		return facultyRepository.findByFirstname(firstname);
	}

	@Override
	public List<Faculty> getFacultyList() {
		return facultyRepository.findAll();
	}

	public List<Faculty> getFacultyByCourseId(Long id) {
		return facultyRepository.getFacultyByCourseId(id);
	}

}
