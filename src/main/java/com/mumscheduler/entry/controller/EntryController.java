package com.mumscheduler.entry.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
	
	@PostMapping("/entries")
	public String createNewEntry(@Valid @ModelAttribute("newEntry") Entry entry, BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
		{
			return "entry/entry-form";
		}
		
		entryService.save(entry);
		return "redirect:/entries";
	}
	
	@GetMapping("/entries/new")
	public String displayNewEntryForm(Model model, @ModelAttribute("newEntry") Entry newEntry)
	{
		String title = "Create a new entry";
		
		model.addAttribute("title",title);
		model.addAttribute("newEntry", newEntry);
		return "entry/entry-form";
	}
}
