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
import com.mumscheduler.schedule.factory.ScheduleFactory;
import com.mumscheduler.schedule.model.Schedule;
import com.mumscheduler.schedule.model.ScheduleFacade;
import com.mumscheduler.schedule.model.ScheduleStatus;
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

	@GetMapping(path = "/schedule", params = { "id" })
	public String getSchedule(Model model, @RequestParam long id) {
		ScheduleFacade sf = ScheduleFactory.getScheduleById(scheduleService, id);

		model.addAttribute("sf", sf);
		model.addAttribute("allowGenerate", false);

		if (sf.isActionSuccess()) {
			model.addAttribute("scheduleId", id);

			if (sf.getSchedule().getStatus() != ScheduleStatus.APPROVED) {
				model.addAttribute("allowGenerate", true);
			}
		}

		return "schedule/schedule";
	}

	@GetMapping(path = "/createSchedule")
	public String createSchedule(@ModelAttribute("newSchedule") Schedule newSchedule, Model model) {
		model.addAttribute("entries", entryService.getEntryList());
		return "schedule/createSchedule";
	}

	@PostMapping("/schedule")
	public String createSchedule(@Valid @ModelAttribute("newSchedule") Schedule schedule, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "redirect:/createSchedule";
		}
		scheduleService.saveSchedule(schedule);
		return "redirect:/schedules";
	}

	@GetMapping(path = "/generateSchedule", params = { "id" })
	public String generateSchedule(@RequestParam long id) {
		ScheduleFacade sf = scheduleService.generateSchedule(id);
		ScheduleFactory.keepStateOnFail(sf);

		return "redirect:/schedule?id=" + id;
	}

	@GetMapping(path = "/deleteSchedule", params = { "id" })
	public String deleteSchedule(Model model, @RequestParam long id) {
		ScheduleFacade sf = ScheduleFactory.getScheduleById(scheduleService, id);

		if (sf.isActionSuccess()) {
			// scheduleService.removeScheduleById(id);
		}

		return "redirect:/schedules";
	}

}
