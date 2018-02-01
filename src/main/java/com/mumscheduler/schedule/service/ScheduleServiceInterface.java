package com.mumscheduler.schedule.service;

import java.util.List;

import com.mumscheduler.schedule.model.Schedule;
import com.mumscheduler.schedule.model.ScheduleFacade;

public interface ScheduleServiceInterface {

	public List<Schedule> getSchedules();

	public ScheduleFacade getScheduleById(long id);

	public void saveSchedule();

	public void generateSchedule();

	public void checkUpdateRequirements(Schedule schedule);

	public void updateSchedule(Schedule schedule);

	public void checkDeleteRequirements(long id);

	public void removeScheduleById(long id);

	public void checkBlockRequirements();

	public void addBlocksToSchedule(Schedule schedule);

	public void checkSectionRequirements(/* Block block */);
	
	public ScheduleFacade validateSchedule(Schedule schedule);
}
