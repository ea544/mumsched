package com.mumscheduler.entry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mumscheduler.entry.model.Entry;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long>
{
	@Query("FROM Entry e where e.entryName=:entryName")
	public Entry getEntryByName(@Param("entryName") String entryName);
	
	public Iterable<Entry> findById(Long id);
	
	
}
