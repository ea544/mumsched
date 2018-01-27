package com.mumscheduler.schedule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ScheduleController {

	@GetMapping(path = "/schedules")
	public String getSchedules() {
		return "schedule/schedules";
	}

	@GetMapping(path = "/schedule", params = {"year", "entry"})
	public String getSchedule(@RequestParam String year, @RequestParam String entry) {
		return "schedule/schedule";
	}

	@GetMapping(path = "/createSchedule")
	public String createSchedule() {
		return "schedule/createSchedule";
	}
}
