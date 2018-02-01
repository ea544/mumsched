package com.mumscheduler.regsection.service;

import java.util.List;

public interface RegSectionInterface {
	
	public List<String> getBlockName(String stdID);
	public List<String> getCourseName(String Block);

}
