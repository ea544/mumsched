
package com.mumscheduler.regsection.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mumscheduler.block.service.BlockService;
import com.mumscheduler.dto.Response;
import com.mumscheduler.entry.model.Entry;
import com.mumscheduler.entry.service.EntryService;
import com.mumscheduler.regsection.service.RegSectionService;
import com.mumscheduler.section.model.Section;
import com.mumscheduler.section.service.SectionService;

@Controller
public class RegSectionController {
	
	@Autowired
	private RegSectionService regSectionService;	
	
	@Autowired
	private EntryService entryService;
	
	@Autowired
	private SectionService sectionService;
	
	@Autowired
	private BlockService blockService;


	@GetMapping("/regsection")
	public String sectionHome(Model model) {	
		/*
		 * TODO: use Entry ID from Student oject
		 */
		Entry entry = entryService.getEntry((long) 3);
		
		System.out.println(entry.getBlocks().size()+ "**************************************************************************");
		model.addAttribute("entry", entry);
		model.addAttribute("blocks",entry.getBlocks());		
		
		return "regsection/regsection";
	}
	
	
	@RequestMapping(value="/regsection/getsectionbyid", method = RequestMethod.GET)
	public @ResponseBody
    Response<List<Section>> getBlockName() {
		List<Section> sectionList = sectionService.getSectionList();
		System.out.println(sectionList);
        return Response.ok(sectionList, HttpStatus.OK.value(), HttpStatus.OK.name());
    }
	
	@RequestMapping(value="/regsection/getcoursebyblockname/{blockName}", method = RequestMethod.GET)
	public @ResponseBody
    Response<List<Section>> getCourseName(@PathVariable("blockName") String blockName) {
		//TODO Change this to getSectionListByBlockName(blockName)
		//Stanley should Create this method in SectionServiceInterface
		
		List<Section> sectionList = sectionService.getSectionList();
		System.out.println(sectionList);
        return Response.ok(sectionList, HttpStatus.OK.value(), HttpStatus.OK.name());
    }
	
	/* @RequestMapping(value="/regsection", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	 public @ResponseBody
	 Response<RegSection> addRegSection(@Valid @RequestBody RegSection regSection) {
	        System.out.println(regSection);	        
	        return Response.ok(null, HttpStatus.OK.value(), HttpStatus.OK.name());
	      
	    }*/
	
}