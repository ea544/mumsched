package com.mumscheduler.entry.service;

import java.util.List;

import com.mumscheduler.entry.model.Entry;


public interface EntryServiceInterface {
	public List<Entry> getEntryList();
	public Entry save(Entry entry);
	public Entry getEntry(Long id);
	public void delete(Long id);
}
