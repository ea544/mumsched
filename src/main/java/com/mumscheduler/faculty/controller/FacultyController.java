package com.mumscheduler.faculty.controller;

import com.mumscheduler.course.service.CourseService;
import com.mumscheduler.faculty.model.Faculty;
import com.mumscheduler.faculty.service.FacultyService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FacultyController {
	
	@Autowired
	private FacultyService facultyService;
	
	@Autowired
	private CourseService courseService;
	
	/**
	 * change this when the URLs change
	 * this variable sets the current tab to active in the HTML
	 */
	private final String activeTab = "faculty";
	
	/**
	 * Display all the courses
	 * @return
	 */
	@GetMapping("/faculty")
	public String facultyHome(Model model) {
		model.addAttribute("activeTab", this.activeTab);
		Iterable<Faculty> faculty = facultyService.getFacultyList();
		model.addAttribute("faculties", faculty);
		return "faculty/faculty-list";
	}
	
	/**
	 * Process creating a new course
	 * Return to the courses form after a course has been saved
	 * @return
	 */
	@PostMapping("/faculty")
	public String createNewFaculty(@Valid @ModelAttribute("faculty") Faculty faculty, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "faculty/faculty-form";
		}
		facultyService.save(faculty);
		return "redirect:/faculty";
	}
	
	/**
	 * Display an empty form to create a new course
	 * 
	 * add all courses to the form, to be displayed in faculty courses 
	 * preferences
	 * to further decouple this, we could call using a webservice
	 * 
	 * @return
	 */
	@GetMapping("/faculty/new")
	public String displayNewFacultyForm(Model model) {
		model.addAttribute("activeTab", this.activeTab);
		model.addAttribute("allCourses", courseService.getCourseList());
		model.addAttribute("faculty", new Faculty());
		return "faculty/faculty-form";
	}
	
	/**
	 * Display a form pre-populated with the course details to edit
	 * add all courses to the form, to be displayed in faculty courses 
	 * preferences
	 * to further decouple this, we could call using a web-service
	 * @return
	 */
	@RequestMapping(value="/faculty/{id}", method=RequestMethod.GET)
	public String displayEditFacultyForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("activeTab", this.activeTab);
		model.addAttribute("allCourses", courseService.getCourseList());
		model.addAttribute("faculty", facultyService.getFaculty(id));
		return "faculty/faculty-form";
	}
	
	/**
	 * Handle updating a course
	 * @return
	 */
	@RequestMapping(value="/faculty/{id}", method=RequestMethod.POST)
	public String updateFaculty() {
		return "redirect:/faculty";
	}
}
