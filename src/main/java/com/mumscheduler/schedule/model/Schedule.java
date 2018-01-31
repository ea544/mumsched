package com.mumscheduler.schedule.model;

import java.rmi.server.UID;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Schedule {
	private UID id;
	private String name;
	// @OneToOne
	// private Entry entry;
	// @ElementCollection
	// @OneToMany
	// private List<Block> blocks;
	// @ElementCollection
	// @OneToMany
	// private List<Section> sections;

	@Enumerated(EnumType.STRING)
	private ScheduleStatus status;

	public UID getId() {
		return id;
	}

	public void setId(UID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ScheduleStatus getStatus() {
		return status;
	}

	public void setStatus(ScheduleStatus status) {
		this.status = status;
	}

}
