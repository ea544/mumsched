package com.mumscheduler.course.service;

import com.mumscheduler.course.model.Course;
import com.mumscheduler.faculty.model.Faculty;

import java.util.List;

public interface CourseServiceInterface {
	
	public List<Course> getCoursePrerequisites(Long id);
	public Course save(Course course);
	public Course getCourse(String code);
	public Course getCourse(Long id);
	public List<Course> getCourseList();
	public Course getCourseByCode(String code);
	public List<Faculty> getFacultyByCourseId(Long id);
}
