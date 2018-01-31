package com.mumscheduler.entry.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mumscheduler.entry.model.Entry;
import com.mumscheduler.entry.repository.EntryRepository;

public class EntryService implements EntryServiceInterface
{
	
	@Autowired
	private EntryRepository entryRepository;
	
	@Override
	public List<Entry> getEntryList() {
		// TODO Auto-generated method stub
		return entryRepository.findAll();
	}

	@Override
	public Entry save(Entry entry) {
		// TODO Auto-generated method stub
		return entryRepository.save(entry);
	}

	@Override
	public Entry getEntry(Long id) {
		// TODO Auto-generated method stub
		return entryRepository.getOne(id);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		entryRepository.delete(id);
	}

}
