package com.mumscheduler.schedule.factory;

import com.mumscheduler.schedule.model.Schedule;
import com.mumscheduler.schedule.model.ScheduleFacade;

public final class ScheduleFactory {

	public static ScheduleFacade createEmptyScheduleFacade() {
		return new ScheduleFacade(true, "", null);
	}

	public static ScheduleFacade createScheduleFacade(Schedule schedule) {
		return new ScheduleFacade(true, "", schedule);
	}
	
}
