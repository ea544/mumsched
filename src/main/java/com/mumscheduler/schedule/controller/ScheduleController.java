package com.mumscheduler.schedule.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mumscheduler.entry.service.EntryServiceInterface;
import com.mumscheduler.schedule.model.Schedule;
import com.mumscheduler.schedule.service.ScheduleServiceInterface;

@Controller
public class ScheduleController {

	@Autowired
	private ScheduleServiceInterface scheduleService;
	@Autowired
	private EntryServiceInterface entryService;

	@GetMapping(path = "/schedules")
	public String getSchedules(Model model) {
		model.addAttribute("schedules", scheduleService.getScheduleList());
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
	public String createSchedule(@ModelAttribute("newSchedule") Schedule newSchedule, Model model) {
		model.addAttribute("entries", entryService.getEntryList());
		return "schedule/createSchedule";
	}
	
	@PostMapping("/schedule")
	public String createSchedule(@Valid @ModelAttribute("newSchedule") Schedule schedule, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "redirect:/createSchedule";
		}
		scheduleService.saveSchedule(schedule);
		return "redirect:/schedules";
	}
	
}
