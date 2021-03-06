package com.mumscheduler.schedule.service;

import java.util.List;
import com.mumscheduler.schedule.model.Schedule;
import com.mumscheduler.schedule.model.ScheduleFacade;

public interface ScheduleServiceInterface {

	public List<Schedule> getScheduleList();

	public ScheduleFacade getScheduleById(long id);

	public Schedule saveSchedule(Schedule schedule);

	public ScheduleFacade generateSchedule(long id);

	public void checkUpdateRequirements(Schedule schedule);

	public Schedule updateSchedule(Schedule schedule);

	public void checkDeleteRequirements(long id);

	public void removeScheduleById(long id);

	public ScheduleFacade checkBlockRequirements(ScheduleFacade sf);

	public ScheduleFacade checkSectionRequirements(ScheduleFacade sf);
}
