package com.mumscheduler.block.controller;

import com.mumscheduler.block.model.Block;
import com.mumscheduler.block.service.BlockService;
import com.mumscheduler.section.service.SectionServiceInterface;

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
public class BlockController {

	@Autowired
	private BlockService blockService;
	
	@Autowired
	private SectionServiceInterface sectionService;
	/**
	 * change this when the URLs change
	 * this variable sets the current tab to active in the HTML
	 */
	private final String activeTab = "blocks";
	
	
	/**
	 * Display all the blocks
	 * @return
	 */
	@GetMapping("/blocks")
	public String getBlockDashboard(Model model) {
		model.addAttribute("activeTab", this.activeTab);
		model.addAttribute("blocks", blockService.getBlockList());
		return "block/block-list";
	}
	
	
	/**
	 * Process creating a new block
	 * Return to the block form after a course has been saved
	 * @return
	 */
	@PostMapping("/blocks")
	public String createNewBlock(@Valid @ModelAttribute("block") Block block, 
			BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "block/block-form";
		}
		blockService.save(block);
		return "redirect:/blocks";
	}
	
	/**
	 * Display an empty form to create a new course
	 * @return
	 */
	@GetMapping("/blocks/new")
	public String getNewBlockForm(Model model) {
		model.addAttribute("activeTab", this.activeTab);
		model.addAttribute("allSections", sectionService.getSectionList());
		model.addAttribute("block", new Block());
		return "block/block-form";
	}
	
	/**
	 * Display a form pre-populated with the course details to edit
	 * @return
	 */
	@RequestMapping(value="/blocks/{id}", method=RequestMethod.GET)
	public String getEditBlockForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("activeTab", this.activeTab);
		model.addAttribute("block", blockService.getBlock(id));
		return "block/block-form";
	}
	
	/**
	 * Handle updating a course
	 * @return
	 */
	@RequestMapping(value="/blocks/{id}", method=RequestMethod.POST)
	public String updateBlock() {
		return "redirect:/blocks";
	}
}
