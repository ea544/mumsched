package com.mumscheduler.schedule.factory;

import com.mumscheduler.schedule.model.Schedule;
import com.mumscheduler.schedule.model.ScheduleFacade;

public final class ScheduleFactory {

	public static final String INSUFFICIENT_BLOCKS = "You don't have a sufficient number of blocks to generate this schedule.";
	public static final String NO_SCHEDULE_FOUND = "No schedule found !";

	public static ScheduleFacade createEmptyScheduleFacade() {
		return new ScheduleFacade(true, "", null);
	}

	public static ScheduleFacade createScheduleFacade(Schedule schedule) {
		return new ScheduleFacade(true, "", schedule);
	}

	public static ScheduleFacade validateSchedule(Schedule schedule) {
		ScheduleFacade sf = ScheduleFactory.createScheduleFacade(schedule);

		if (schedule == null) {
			sf.setActionSuccess(false);
			sf.setActionResponse(NO_SCHEDULE_FOUND);
		}

		return sf;
	}

}
