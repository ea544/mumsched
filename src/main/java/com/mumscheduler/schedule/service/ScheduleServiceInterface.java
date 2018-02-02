package com.mumscheduler.schedule.service;

import java.util.List;

import com.mumscheduler.schedule.model.Schedule;
import com.mumscheduler.schedule.model.ScheduleFacade;

public interface ScheduleServiceInterface {

	public List<Schedule> getScheduleList();

	public ScheduleFacade getScheduleById(long id);

	public Schedule saveSchedule(Schedule schedule);

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
