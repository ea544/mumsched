package com.mumscheduler.block.model;

import com.mumscheduler.section.model.Section;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Block {
	
	@Id
	@GeneratedValue
	private Long id;
	
	//required
	private String name;
	
	//required
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startdate;
	
	//required
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate enddate;
	
	@OneToMany
	private Set<Section> sections;
	
	private Integer serial;
	
	public Block() {}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Set<Section> getSections() {
		return sections;
	}

	public void setSections(Set<Section> sections) {
		this.sections = sections;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getStartdate() {
		return startdate;
	}
	public void setStartdate(LocalDate startdate) {
		this.startdate = startdate;
	}
	public LocalDate getEnddate() {
		return enddate;
	}
	public void setEnddate(LocalDate enddate) {
		this.enddate = enddate;
	}
	public Integer getSerial() {
		return serial;
	}
	public void setSerial(Integer serial) {
		this.serial = serial;
	}
	
	
	@Override
	public String toString() {
		return String.format("%s Block - %s", name, startdate.toString());
	}
	
	/*
	 * for some reason this method won't let the block form load
	 * due to a NullPointerException
	@Override
	public int hashCode() {
		return (5 * name.hashCode() + startdate.hashCode());
	}
	*/
}
