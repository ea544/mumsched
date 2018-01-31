package com.mumscheduler.entry.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mumscheduler.entry.model.Entry;
import com.mumscheduler.entry.service.EntryService;

@Controller
public class EntryController 
{
	@Autowired
	private EntryService entryService;
	
	@GetMapping("/entries")
	public String entryHome(Model model) 
	{
		String title = "Manage Entry";
		List<Entry> entries = entryService.getEntryList();
		model.addAttribute("entries", entries);
		model.addAttribute("title", title);
		return "entry/entry-list";
	}
}
