package com.mumscheduler.section.controller;

import com.mumscheduler.course.model.Course;
import com.mumscheduler.course.service.CourseService;
import com.mumscheduler.section.model.Section;
import com.mumscheduler.section.service.SectionService;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.mumscheduler.faculty.model.Faculty;
import com.mumscheduler.faculty.service.FacultyService;




//import java.util.function.Consumer;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
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
	
	@Autowired
	private FacultyService facultyService;
	
	//@Autowired
	//String title = "Manage Section";
	
	/**
	 * Display all the section
	 * @return
	 */
	@GetMapping("/sections")
	public String sectionHome(Model model) {
		String title = "Manage Section";
		Iterable<Section> sections = sectionService.getSectionList();
		model.addAttribute("sections", sections);
		model.addAttribute("title", title);
		return "section/section-list";
	}
	
	/**
	 * Process creating a new section
	 * Return to the courses form after a course has been saved
	 * @return
	 */
	@PostMapping("/sections")
	public String createNew(@Valid @ModelAttribute("section")  Section section, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "section/section-form";
		}
		
		String error;
		System.out.println(""+section.getCourse());
		try
		{
			
			sectionService.save(section);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			error = "Course name and Faculty name are already exist!";
			
			String title = "Create a new section";
			//model.addAttribute("allSection", sectionService.getSectionList());
			Iterable<Course> courses = courseService.getCourseList();
			Iterable<Faculty> faculties = facultyService.getFacultyList();
			model.addAttribute("courses", courses);
			model.addAttribute("faculties",faculties);
			//model.addAttribute("section", new Section()); change to
			model.addAttribute("section", section);
			model.addAttribute("title", title);
			
			model.addAttribute("error", error);
			//return "section/section-form";
			System.out.println( section.getId());
			//System.out.println( id);
			return "section/section-form";
		}
		return "redirect:/sections";
	}
	
	/**
	 * Display an empty form to create a new course
	 * 
	 * add all section to the form, to be displayed in  sections 
	 * preferences
	 * to further decouple this, we could call using a webservice
	 * 
	 * @return
	 */
	@GetMapping("/sections/new")
	public String displayNewSectionForm(Model model, @ModelAttribute("section") Section newSection) {
		String title = "Create a new section";
		//model.addAttribute("allSection", sectionService.getSectionList());
		Iterable<Course> courses = courseService.getCourseList();
		Iterable<Faculty> faculties = facultyService.getFacultyList();
		model.addAttribute("courses", courses);
		model.addAttribute("faculties",faculties);
		//model.addAttribute("section", new Section()); change to
		model.addAttribute("section", newSection);
		model.addAttribute("title", title);
		
		courses.forEach((c) -> System.out.println(c.getId()+" "+c.getName()));
		faculties.forEach((f) -> System.out.println(f.getFirstname()+" "+f.getLastname()));
		/*for(Course c : courses)
		{
			System.out.println(c.getId()+" "+c.getName());
			//System.out.println(c.getId());
		}
		*/
		
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
		String title = "Update section";
		Iterable<Course> courses = courseService.getCourseList();
		Iterable<Faculty> faculties = facultyService.getFacultyList();
		Section section = sectionService.getSection(id);
		model.addAttribute("section", section);
		model.addAttribute("faculties",faculties);
		model.addAttribute("courses", courses);
		model.addAttribute("title", title);
		
		//System.out.println(section.getBlockName()+ ' ' +section.getCourseName());
		
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