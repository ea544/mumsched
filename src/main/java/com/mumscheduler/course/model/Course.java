package com.mumscheduler.course.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Course {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	
	private String code;
	
	private Integer level;
	
	private Integer capacity;	

	@OneToMany
	private Set<Course> prerequisites;
	
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
	
	public Set<Course> getPrerequisites() {
		return prerequisites;
	}

	public void setPrerequisites(Set<Course> prerequisites) {
		this.prerequisites = prerequisites;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
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
