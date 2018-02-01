package com.mumscheduler.regsection.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mumscheduler.regsection.model.RegSection;

public interface RegSectionRepository extends JpaRepository<RegSection, String> {
	
}
