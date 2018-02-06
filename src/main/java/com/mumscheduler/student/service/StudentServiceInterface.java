package com.mumscheduler.student.service;

import com.mumscheduler.student.model.Student;

import java.util.List;

public interface StudentServiceInterface {
	public Student save(Student student);
	public Student getStudent(String email);
	public Student getStudent(Long id);
	public Iterable<Student> findStudentByFirstname(String firstname);
	public List<Student> getStudentList();
}
