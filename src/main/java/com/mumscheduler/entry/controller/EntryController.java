package com.mumscheduler.entry.controller;

import com.mumscheduler.block.service.BlockServiceInterface;
import com.mumscheduler.entry.model.Entry;
import com.mumscheduler.entry.service.EntryService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EntryController 
{
	@Autowired
	private EntryService entryService;
	
	@Autowired
	private BlockServiceInterface blockService;
	
	@GetMapping("/entries")
	public String entryHome(Model model) 
	{
		String title = "Manage Entry";
		List<Entry> entries = entryService.getEntryList();
		model.addAttribute("entries", entries);
		model.addAttribute("title", title);
		model.addAttribute("allBlocks", blockService.getBlockList());
		for(Entry e : entries)
		{
			System.out.println(e.getBlocks());
		}
		
		return "entry/entry-list";
	}
	
	@PostMapping("/entries")
	public String createNewEntry(@Valid @ModelAttribute("entry") Entry entry, BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
		{
			return "entry/entry-form";
		}
		
		entryService.save(entry);
		return "redirect:/entries";
	}
	
	@GetMapping("/entries/new")
	public String displayNewEntryForm(Model model, @ModelAttribute("entry") Entry entry)
	{
		String title = "Create a new entry";
		
		model.addAttribute("title",title);
		model.addAttribute("entry", entry);
		model.addAttribute("allBlocks", blockService.getAvailableBlocks(Date.valueOf(LocalDate.now())));
		return "entry/entry-form";
	}
	
	
	@RequestMapping(value="/entriesUpdate/{id}", method=RequestMethod.GET)
	public String displayEditForm(@PathVariable("id") Long id, Model model) {
		//model.addAttribute("allCourses", courseService.getCourseList());
		String title = "Update entry";
		Iterable<Entry> entries = entryService.getEntryList();
		//Iterable<Faculty> faculties = facultyService.getFacultyList();
		Entry entry = entryService.getEntry(id);
		model.addAttribute("entry", entry);
		//model.addAttribute("faculties",faculties);
		model.addAttribute("entries", entries);
		model.addAttribute("allBlocks", blockService.getAvailableBlocks(Date.valueOf(LocalDate.now())));
		model.addAttribute("title", title);
		
		//System.out.println(section.getBlockName()+ ' ' +section.getCourseName());
		
		return "entry/entry-form";
	}
	
	@RequestMapping(value="/entriesDelete/{id}", method=RequestMethod.GET)
	public String delete(@PathVariable("id") Long id, Model model) {
		//model.addAttribute("allCourses", courseService.getCourseList());
		//model.addAttribute("section", sectionService.getSection(id));
		entryService.delete(id);
		return "redirect:/entries";
	}
	
	
	
}
