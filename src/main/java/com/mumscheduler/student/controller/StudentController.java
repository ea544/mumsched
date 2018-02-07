package com.mumscheduler.student.controller;

import com.mumscheduler.block.service.BlockServiceInterface;
import com.mumscheduler.course.service.CourseServiceInterface;
import com.mumscheduler.student.model.Student;
import com.mumscheduler.student.service.StudentServiceInterface;
import com.mumscheduler.student.validator.StudentValidator;

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
public class StudentController {

	@Autowired
	private StudentServiceInterface studentService;

	@Autowired
	private BlockServiceInterface blockService;

	@Autowired
	private CourseServiceInterface courseService;

	@Autowired
	private StudentValidator studentValidator;
	
	/**
	 * change this when the URLs change this variable sets the current tab to active
	 * in the HTML
	 */
	private final String activeTab = "student";

	/**
	 * Display all the courses
	 * 
	 * @return
	 */
	@GetMapping("/students")
	public String studentHome(Model model) {
		model.addAttribute("activeTab", this.activeTab);
		Iterable<Student> student = studentService.getStudentList();
		model.addAttribute("students", student);
		return "student/student-list";
	}

	/**
	 * Process creating a new course Return to the courses form after a course has
	 * been saved
	 * 
	 * @return
	 */
	@PostMapping("/students")
	public String createNewStudent(@Valid @ModelAttribute("student") Student student, 
			BindingResult bindingResult, Model model) {
		studentValidator.validate(student, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("activeTab", this.activeTab);
			model.addAttribute("student", student);
			model.addAttribute("allCourses", courseService.getCourseList());
			model.addAttribute("allBlocks", blockService.getBlockList());
			return "student/student-form";
		}
		studentService.save(student);
		return "redirect:/students";
	}

	/**
	 * Display an empty form to create a new course
	 * 
	 * add all courses to the form, to be displayed in student courses preferences
	 * to further decouple this, we could call using a webservice
	 * 
	 * @return
	 */
	@GetMapping("/students/new")
	public String displayNewStudentForm(Model model, @ModelAttribute("student") Student student) {
		model.addAttribute("activeTab", this.activeTab);
		model.addAttribute("allCourses", courseService.getCourseList());
		model.addAttribute("allBlocks", blockService.getBlockList());
		model.addAttribute("student", student);
		return "student/student-form";
	}

	/**
	 * Display a form pre-populated with the course details to edit add all courses
	 * to the form, to be displayed in student courses preferences to further
	 * decouple this, we could call using a web-service
	 * 
	 * @return
	 */
	@RequestMapping(value = "/students/{id}", method = RequestMethod.GET)
	public String displayEditStudentForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("activeTab", this.activeTab);
		model.addAttribute("allCourses", courseService.getCourseList());
		model.addAttribute("student", studentService.getStudent(id));
		model.addAttribute("allBlocks", blockService.getBlockList());
		return "student/student-form";
	}

	/**
	 * Handle updating a course
	 * 
	 * @return
	 */
	@RequestMapping(value = "/students/{id}", method = RequestMethod.POST)
	public String updateStudent() {
		return "redirect:/student-list";
	}
	
	/**
	 * Handle updating a course
	 * 
	 * @return
	 */
	@RequestMapping(value = "/students/{id}/profile", method = RequestMethod.GET)
	public String viewStudentProfile(Model model, @PathVariable("id") Long id) {
		model.addAttribute("activeTab", this.activeTab);
		model.addAttribute("student", studentService.getStudent(id));
		model.addAttribute("registeredSections", "registeredSections");
		return "student/student";
	}
}
