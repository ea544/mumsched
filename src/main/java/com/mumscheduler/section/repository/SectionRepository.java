package com.mumscheduler.section.repository;

import com.mumscheduler.section.model.Section;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
	/**
	 * Get a course by code
	 */
	@Query("FROM Section s WHERE s.blockName=:blockName")
	public Section getSectionByBlockName(@Param("blockName") String blockName);
	
	/**
	 * Get a course by code
	 */
	public Iterable<Section> findById(Long id);
}
