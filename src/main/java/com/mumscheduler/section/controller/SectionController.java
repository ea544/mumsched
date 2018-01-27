package com.mumscheduler.section.controller;

import com.mumscheduler.course.service.CourseService;
import com.mumscheduler.section.model.Section;
import com.mumscheduler.section.service.SectionService;

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
public class SectionController {
	
	@Autowired
	private SectionService sectionService;
	
	@Autowired
	private CourseService courseService;
	/**
	 * Display all the courses
	 * @return
	 */
	@GetMapping("/sections")
	public String sectionHome(Model model) {
		Iterable<Section> sections = sectionService.getSectionList();
		model.addAttribute("sections", sections);
		return "section/section-list";
	}
	
	/**
	 * Process creating a new course
	 * Return to the courses form after a course has been saved
	 * @return
	 */
	@PostMapping("/sections")
	public String createNew(@Valid @ModelAttribute("section")  Section section, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "section/section-form";
		}
		sectionService.save(section);
		return "redirect:/sections";
	}
	
	/**
	 * Display an empty form to create a new course
	 * 
	 * add all courses to the form, to be displayed in  courses 
	 * preferences
	 * to further decouple this, we could call using a webservice
	 * 
	 * @return
	 */
	@GetMapping("/sections/new")
	public String displayNewSectionForm(Model model) {
		model.addAttribute("allSection", sectionService.getSectionList());
		model.addAttribute("section", new Section());
		return "section/section-form";
	}
	
	/**
	 * Display a form pre-populated with the course details to edit
	 * add all courses to the form, to be displayed in  courses 
	 * preferences
	 * to further decouple this, we could call using a web-service
	 * @return
	 */
	
	@RequestMapping(value="/sectionsUpdate/{id}", method=RequestMethod.GET)
	public String displayEditForm(@PathVariable("id") Long id, Model model) {
		//model.addAttribute("allCourses", courseService.getCourseList());
		model.addAttribute("section", sectionService.getSection(id));
		return "section/section-form";
	}
	
	@RequestMapping(value="/sectionsDelete/{id}", method=RequestMethod.GET)
	public String delete(@PathVariable("id") Long id, Model model) {
		//model.addAttribute("allCourses", courseService.getCourseList());
		//model.addAttribute("section", sectionService.getSection(id));
		sectionService.delete(id);
		return "redirect:/sections";
	}
	
	
	/**
	 * Handle updating a course
	 * @return
	 */
	
	/*@RequestMapping(value="/sectionsDelete/{id}", method=RequestMethod.GET)
	public String delete() {
		return "redirect:/sections";
	}*/
	
}
