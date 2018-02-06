package com.mumscheduler.block.validator;

import com.mumscheduler.block.model.Block;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class BlockValidator implements Validator {

	@Autowired
	private Environment env;

	@Override
	public boolean supports(Class<?> classToValidate) {
		return Block.class.equals(classToValidate);
	}

	@Override
	public void validate(Object object, Errors errors) {
		Block block = (Block) object;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.notnull", env.getProperty("name.notnull"));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startdate", "startdate.notnull", env.getProperty("startdate.notnull"));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "enddate", "enddate.notnull", env.getProperty("enddate.notnull"));
		
		if(block.getEnddate() != null && block.getStartdate() != null && block.getEnddate().compareTo(block.getStartdate()) < 0) {
			errors.rejectValue("enddate", "block.datediff", env.getProperty("block.datediff"));
		}
	}

}
