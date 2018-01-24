package com.mumscheduler.course.controller;

import com.mumscheduler.course.model.Course;
import com.mumscheduler.course.service.CourseService;
import com.mumscheduler.faculty.service.FacultyService;

import java.util.List;

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
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private FacultyService facultyService;
	/**
	 * Display all the courses
	 * @return
	 */
	@GetMapping("/courses")
	public String coursesHome(Model model) {
		List<Course> courses = courseService.getCourseList();
		model.addAttribute("courses", courses);
		return "course/course-list";
	}
	
	/**
	 * Process creating a new course
	 * Return to the courses form after a course has been saved
	 * @return
	 */
	@PostMapping("/courses")
	public String createNewCourse(@Valid @ModelAttribute("course") Course course, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "course/course-form";
		}
		courseService.save(course);
		return "redirect:/courses";
	}
	
	/**
	 * Display an empty form to create a new course
	 * @return
	 */
	@GetMapping("/courses/new")
	public String displayNewCourseForm(Model model) {
		model.addAttribute("allFaculty", facultyService.getFacultyList());
		model.addAttribute("course", new Course());
		return "course/course-form";
	}
	
	/**
	 * Display a form pre-populated with the course details to edit
	 * @return
	 */
	@RequestMapping(value="/courses/{id}", method=RequestMethod.GET)
	public String displayEditCourseForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("allFaculty", facultyService.getFacultyList());
		model.addAttribute("course", courseService.getCourse(id));
		return "course/course-form";
	}
	
	/**
	 * Handle updating a course
	 * @return
	 */
	@RequestMapping(value="/courses/{id}", method=RequestMethod.POST)
	public String updateCourse() {
		return "redirect:/courses";
	}
}
