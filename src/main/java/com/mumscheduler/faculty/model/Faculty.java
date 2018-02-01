package com.mumscheduler.faculty.model;

import com.mumscheduler.block.model.Block;
import com.mumscheduler.course.model.Course;
import com.mumscheduler.security.model.User;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Faculty extends User{

	@ManyToMany(mappedBy="faculty")
	private Set<Course> courses;
	
	@ManyToMany
	private Set<Block> blocks;
	
	public Faculty() {}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public Set<Block> getBlocks() {
		return blocks;
	}

	public void setBlocks(Set<Block> blocks) {
		this.blocks = blocks;
	}
	
	
	
}
