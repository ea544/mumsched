package com.mumscheduler.schedule.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mumscheduler.schedule.model.Schedule;
import com.mumscheduler.schedule.model.ScheduleFacade;
import com.mumscheduler.schedule.repository.ScheduleRepository;

@Service
public class ScheduleService implements ScheduleServiceInterface {

	@Autowired
	private ScheduleRepository repo;

	@Override
	public List<Schedule> getSchedules() {
		List<Schedule> schedules = new ArrayList<>();
		repo.findAll().forEach(schedules::add);
		return schedules;
	}

	@Override
	public ScheduleFacade getScheduleById(long id) {
		Schedule schedule = repo.findOne(id);
		return validateSchedule(schedule);
	}

	@Override
	public void saveSchedule() {
		// TODO Auto-generated method stub

	}

	@Override
	public void generateSchedule() {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkUpdateRequirements(Schedule schedule) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateSchedule(Schedule schedule) {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkDeleteRequirements(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeScheduleById(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkBlockRequirements() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addBlocksToSchedule(Schedule schedule) {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkSectionRequirements() {
		// TODO Auto-generated method stub

	}

	@Override
	public ScheduleFacade validateSchedule(Schedule schedule) {
		ScheduleFacade sf = new ScheduleFacade(true, "", schedule);

		if (schedule == null) {
			sf.setActionSuccess(false);
			sf.setActionResponse("No schedule found!");
		}

		return sf;
	}

}
