package com.mumscheduler.schedule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mumscheduler.schedule.service.ScheduleServiceInterface;

@Controller
public class ScheduleController {

	@Autowired
	private ScheduleServiceInterface scheduleService;

	@GetMapping(path = "/schedules")
	public String getSchedules(Model model) {
		model.addAttribute("schedules", scheduleService.getSchedules());
		return "schedule/schedules";
	}

//	@GetMapping(path = "/schedule", params = { "year", "entry" })
//	public String getSchedule(@RequestParam String year, @RequestParam String entry) {
//		return "schedule/schedule";
//	}

	@GetMapping(path = "/schedule", params= {"id"})
	public String getSchedule(Model model, @RequestParam long id) {
		model.addAttribute("sf", scheduleService.getScheduleById(id));
		return "schedule/schedule";
	}

	@GetMapping(path = "/createSchedule")
	public String createSchedule() {
		return "schedule/createSchedule";
	}
}
