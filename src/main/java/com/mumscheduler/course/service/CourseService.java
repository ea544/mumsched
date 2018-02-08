package com.mumscheduler.course.service;

import com.mumscheduler.course.model.Course;
import com.mumscheduler.course.repository.CourseRepository;
import com.mumscheduler.faculty.model.Faculty;
import com.mumscheduler.faculty.service.FacultyService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService implements CourseServiceInterface {

	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private FacultyService facultyService;
	@Override
	public Course save(Course course) {
		return courseRepository.save(course);
	}

	@Override
	public Course getCourse(String code) {
		return courseRepository.getCourseByCode(code);
	}

	@Override
	public Course getCourse(Long id) {
		return courseRepository.findOne(id);
	}
	
	@Override
	public Course getCourseByCode(String code) {
		return courseRepository.getCourseByCode(code);
	}

	@Override
	public List<Course> getCourseList() {
		return courseRepository.findAll();
	}
	
	@Override
	public List<Course> getCoursePrerequisites(Long id){
		return courseRepository.getCoursePrerequisites(id);
	}

	@Override
	public List<Faculty> getFacultyByCourseId(Long id){
		return facultyService.getFacultyByCourseId(id);
	}
}
