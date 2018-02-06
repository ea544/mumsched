package com.mumscheduler.section.model;

import com.mumscheduler.course.model.Course;
import com.mumscheduler.faculty.model.Faculty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(
	    name="Section", 
	    uniqueConstraints=
	        @UniqueConstraint(columnNames={"course_id", "faculty_user_id"})
	)
public class Section {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	//@NotNull
	//private Block block; // Block
	
	
	@NotNull
	private int capacity;
	@NotNull(message="Course cannot be null")
	@OneToOne
	private Course course;
	//private Entry entry;
	@OneToOne
	private Faculty faculty; 
	//private String track;
	
	
	//@ManyToMany(mappedBy="section")
	//private Set<Course> courses;
	
	public Section() {}

	public Section(int capacity, Course course, Faculty faculty) {
		super();
		this.capacity = capacity;
		this.course = course;
		this.faculty = faculty;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}
	
}
