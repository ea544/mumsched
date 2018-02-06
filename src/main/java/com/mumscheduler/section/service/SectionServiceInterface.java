package com.mumscheduler.section.service;

import com.mumscheduler.section.model.Section;

import java.util.List;

public interface SectionServiceInterface {
	public Section save(Section section);
	public void delete(Long id);
	//public Section getSection(String blockName);
	public Section getSection(Long id);
	public Iterable<Section> findSectionById(Long id);
	public List<Section> getSectionList();
}
