package com.mumscheduler.section.service;

import com.mumscheduler.section.model.Section;
import com.mumscheduler.section.repository.SectionRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectionService implements SectionServiceInterface {

	@Autowired
	private SectionRepository sectionRepository;
	/**
	 * Save the Section
	 */
	@Override
	public Section save(Section section) {
		return sectionRepository.save(section);
	}
	
	@Override
	public void delete(Long id) {
		sectionRepository.delete(id);
	}
	

	/*@Override
	public Section getSection(String blockName) {
		return sectionRepository.getSectionByBlockName(blockName);
	}*/

	@Override
	public Section getSection(Long id) {
		return sectionRepository.findOne(id);
	}
	
	/*@Override
	public Iterable<Section> findSectionById(Long id) {
		return sectionRepository.findAll(id);
	}*/

	@Override
	public List<Section> getSectionList() {
		return (List<Section>)sectionRepository.findAll();
	}

	@Override
	public Iterable<Section> findSectionById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
