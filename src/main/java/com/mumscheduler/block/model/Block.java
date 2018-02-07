package com.mumscheduler.block.model;

import com.mumscheduler.section.model.Section;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Block {

	@Id
	@GeneratedValue
	private Long id;

	// required
	private String name;

	// required
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date startdate;

	// required
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date enddate;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "block_sections", joinColumns = @JoinColumn(name = "block_id"), inverseJoinColumns = @JoinColumn(name = "section_id"))
	private Set<Section> sections;

	private Integer serial;

	public Block() {
	}

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

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
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
	 * for some reason this method won't let the block form load due to a
	 * NullPointerException
	 * 
	 * @Override public int hashCode() { return (5 * name.hashCode() +
	 * startdate.hashCode()); }
	 */
}
