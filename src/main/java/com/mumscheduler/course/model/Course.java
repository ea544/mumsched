package com.mumscheduler.course.model;

import com.mumscheduler.faculty.model.Faculty;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Course {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@NotNull
	private String code;
	
	@NotNull
	private Integer level;
	
	@ManyToMany
	private Set<Faculty> faculty;
	
	public Set<Faculty> getFaculty() {
		return faculty;
	}

	public void setFaculty(Set<Faculty> faculty) {
		this.faculty = faculty;
	}

	public Course() {}

	public Course(String name, String code, Integer level) {
		super();
		this.name = name;
		this.code = code;
		this.level = level;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
	@Override
	public String toString() {
		return String.format("%s - %s", code, name);
	}
	
}
