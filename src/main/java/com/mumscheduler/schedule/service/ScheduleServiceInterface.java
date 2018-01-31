package com.mumscheduler.schedule.service;

import java.rmi.server.UID;
import java.util.List;

import com.mumscheduler.schedule.model.Schedule;

public interface ScheduleServiceInterface {

	public List<Schedule> getSchedules();

	public Schedule getScheduleDetails();

	public void saveSchedule();

	public void generateSchedule();

	public void checkUpdateRequirements(Schedule schedule);

	public void updateSchedule(Schedule schedule);

	public void checkDeleteRequirements(UID id);

	public void removeScheduleById(UID id);

	public void checkBlockRequirements();

	public void addBlocksToSchedule(Schedule schedule);

	public void checkSectionRequirements(/* Block block */);
}
