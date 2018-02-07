package com.mumscheduler.schedule.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.mumscheduler.block.model.Block;
import com.mumscheduler.entry.model.Entry;

@Entity
public class Schedule {

	@Id
	@GeneratedValue
	private long id;
	@NotEmpty(message = "A name for the schedule is needed.")
	private String name;
	@NotNull(message = "A valid entry is needed.")
	@OneToOne(cascade = CascadeType.ALL)
	private Entry entry;

	@Enumerated(EnumType.STRING)
	private ScheduleStatus status = ScheduleStatus.DRAFT;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Entry getEntry() {
		return entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}

	public Set<Block> getBlocks() {
		return this.entry.getBlocks();
	}

	public void setBlocks(Set<Block> blocks) {
		this.entry.setBlocks(blocks);
	}

	public ScheduleStatus getStatus() {
		return status;
	}

	public void setStatus(ScheduleStatus status) {
		this.status = status;
	}

}
