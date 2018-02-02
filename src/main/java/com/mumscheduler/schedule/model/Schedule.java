package com.mumscheduler.schedule.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.mumscheduler.block.model.Block;
import com.mumscheduler.entry.model.Entry;
import com.mumscheduler.section.model.Section;

@Entity
public class Schedule {

	@Id
	@GeneratedValue
	private long id;
	@NotEmpty(message = "A name for the schedule is needed.")
	private String name;
	@NotNull(message = "A valid entry is needed.")
	@OneToOne
	private Entry entry;
	@ElementCollection
	@OneToMany
	private List<Block> blocks;
	@ElementCollection
	@OneToMany
	private List<Section> sections;

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

	public List<Block> getBlocks() {
		return blocks;
	}

	public void setBlocks(List<Block> blocks) {
		this.blocks = blocks;
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public ScheduleStatus getStatus() {
		return status;
	}

	public void setStatus(ScheduleStatus status) {
		this.status = status;
	}

}
