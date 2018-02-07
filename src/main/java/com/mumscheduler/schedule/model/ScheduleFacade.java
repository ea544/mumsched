package com.mumscheduler.schedule.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.mumscheduler.block.model.Block;
import com.mumscheduler.section.model.Section;

public class ScheduleFacade {

	private boolean actionSuccess;
	private String actionResponse;
	private Schedule schedule;

	public ScheduleFacade(boolean actionSuccess, String actionResponse, Schedule schedule) {
		setActionSuccess(actionSuccess);
		setActionResponse(actionResponse);
		setSchedule(schedule);
	}

	public boolean isActionSuccess() {
		return actionSuccess;
	}

	public void setActionSuccess(boolean actionSuccess) {
		this.actionSuccess = actionSuccess;
	}

	public String getActionResponse() {
		return actionResponse;
	}

	public void setActionResponse(String actionResponse) {
		this.actionResponse = actionResponse;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public int getFppEstimate() {
		return getSchedule().getEntry().getFppNum();
	}

	public int getMppEstimate() {
		return getSchedule().getEntry().getMppNum();
	}

	public int getTotalExpectedStudents() {
		return getFppEstimate() + getMppEstimate();
	}

	public Set<Block> getBlocksForSchedule() {
		return this.schedule.getEntry().getBlocks();
	}

	public Set<Section> getAllSections() {
		Set<Section> sections = new HashSet<Section>();

		getBlocksForSchedule().forEach(b -> {
			sections.addAll(b.getSections());
		});

		return sections;
	}

	public Set<Section> getSectionsByBlock(Block block) {
		return block.getSections();
	}

	public Set<Section> getSectionsByBlock(Block block, String courseCode) {
		return block.getSections().stream().filter(s -> s.getCourse().getCode() == courseCode)
				.collect(Collectors.toSet());
	}

	public Block getFirstBlock() {
		Optional<Block> block = getBlocksForSchedule().stream()
				.sorted((b1, b2) -> b1.getStartdate().compareTo(b2.getStartdate())).findFirst();

		return block.orElse(null);
	}

	public Block getSecondBlock() {
		Optional<Block> block = getBlocksForSchedule().stream()
				.sorted((b1, b2) -> b1.getStartdate().compareTo(b2.getStartdate())).skip(1).findFirst();

		return block.orElse(null);
	}

	public List<Block> getRemainingBlocks() {
		List<Block> blocks = getBlocksForSchedule().stream()
				.sorted((b1, b2) -> b1.getStartdate().compareTo(b2.getStartdate())).skip(2).map(b -> b)
				.collect(Collectors.toList());

		return blocks;
	}

}
