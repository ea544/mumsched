package com.mumscheduler.course.service;

import com.mumscheduler.course.model.Course;

import java.util.List;

public interface CourseServiceInterface {
	
	public List<Course> getCoursePrequisites(Long id);
	public Course save(Course course);
	public Course getCourse(String code);
	public Course getCourse(Long id);
	public List<Course> getCourseList();
}
