package com.mumscheduler.schedule.model;

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

}
