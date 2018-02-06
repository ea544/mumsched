package com.mumscheduler.course.validator;

import com.mumscheduler.course.model.Course;
import com.mumscheduler.course.service.CourseServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@PropertySource("classpath:i18n/validation/course.properties")
@Component
public class CourseValidator implements Validator {

	@Autowired
	private Environment env;

	@Autowired
	private CourseServiceInterface courseService;
	
	@Override
	public boolean supports(Class<?> classToValidate) {
		return Course.class.equals(classToValidate);
	}

	@Override
	public void validate(Object object, Errors errors) {
		Course course = (Course) object;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.notnull", env.getProperty("name.notnull"));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "code.notnull", env.getProperty("code.notnull"));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "level", "level.notnull", env.getProperty("level.notnull"));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "capacity", "capacity.notnull", env.getProperty("capacity.notnull"));
		
		/**
		 * When we save we check if the code is unique
		 */
		if( course.getId() == null) {
			if(course.getCode() != null && courseService.getCourseByCode(course.getCode()) != null) {
				errors.rejectValue("code", "code.unique", env.getProperty("code.unique"));
			}
		}
		
		/**
		 * check if a course is a prerequisite of itself
		 */
		if( course.getCode() != null && course.getPrerequisites().contains(course)) {
			errors.rejectValue("prerequisites", "self.prerequisite", env.getProperty("self.prerequisite"));
		}
	}
	

}
