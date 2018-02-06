package com.mumscheduler.student.service;

import com.mumscheduler.student.model.Student;
import com.mumscheduler.student.repository.StudentRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements StudentServiceInterface {

	@Autowired
	private StudentRepository studentRepository;
	/**
	 * Save the student
	 */
	@Override
	public Student save(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public Student getStudent(String email) {
		return studentRepository.getStudentByEmail(email);
	}

	@Override
	public Student getStudent(Long id) {
		return studentRepository.getOne(id);
	}
	
	@Override
	public Iterable<Student> findStudentByFirstname(String firstname) {
		return studentRepository.findByFirstname(firstname);
	}

	@Override
	public List<Student> getStudentList() {
		return studentRepository.findAll();
	}

}
